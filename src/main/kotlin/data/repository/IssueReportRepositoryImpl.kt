package com.actaks.data.repository

import com.actaks.data.database.entity.IssueReportEntity
import com.actaks.data.mapper.toIssueReport
import com.actaks.data.mapper.toIssueReportEntity
import com.actaks.data.util.Constant.ISSUE_REPORTS_COLLECTION_NAME
import com.actaks.domain.model.IssueReport
import com.actaks.domain.repository.IssueReportRepository
import com.actaks.domain.util.DataError
import com.actaks.domain.util.Result
import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId

class IssueReportRepositoryImpl(
    database: MongoDatabase
) : IssueReportRepository {
    val issueReportCollection = database.getCollection<IssueReportEntity>(ISSUE_REPORTS_COLLECTION_NAME)


    override suspend fun getAllIssueReports(): Result<List<IssueReport>, DataError> {
        return try {
            val issueReports = issueReportCollection
                .find()
                .map { it.toIssueReport() }
                .toList()

            if (issueReports.isNotEmpty()) {
                Result.Success(issueReports)
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun insertIssueReport(
        issueReport: IssueReport
    ): Result<Unit, DataError> {
        return try {
            issueReportCollection.insertOne(issueReport.toIssueReportEntity())
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun deleteIssueReportById(
        id: String?
    ): Result<Unit, DataError> {
        if (id.isNullOrBlank()) {
            return Result.Failure(DataError.Validation)
        }
        return try {
            val filterQuery = Filters.eq(IssueReportEntity::_id.name, ObjectId(id))
            val deletedIssue = issueReportCollection.deleteOne(filterQuery)

            if (deletedIssue.deletedCount > 0) {
                Result.Success(Unit)
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }
}