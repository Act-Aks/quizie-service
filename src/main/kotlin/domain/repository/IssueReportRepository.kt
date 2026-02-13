package com.actaks.domain.repository

import com.actaks.domain.model.IssueReport
import com.actaks.domain.util.DataError
import com.actaks.domain.util.Result

interface IssueReportRepository {
    suspend fun getAllIssueReports(): Result<List<IssueReport>, DataError>
    suspend fun insertIssueReport(
        issueReport: IssueReport
    ): Result<Unit, DataError>

    suspend fun deleteIssueReportById(
        id: String?
    ): Result<Unit, DataError>
}