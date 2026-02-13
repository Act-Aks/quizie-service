package com.actaks.data.repository

import com.actaks.data.database.entity.QuizTopicEntity
import com.actaks.data.mapper.toQuizTopic
import com.actaks.data.mapper.toQuizTopicEntity
import com.actaks.data.util.Constant.TOPICS_COLLECTION_NAME
import com.actaks.domain.model.QuizTopic
import com.actaks.domain.repository.QuizTopicRepository
import com.actaks.domain.util.DataError
import com.actaks.domain.util.Result
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId

class QuizTopicRepositoryImpl(
    database: MongoDatabase
) : QuizTopicRepository {
    private val topicCollection = database
        .getCollection<QuizTopicEntity>(TOPICS_COLLECTION_NAME)

    override suspend fun deleteTopic(id: String?): Result<Unit, DataError> {
        if (id.isNullOrBlank()) {
            return Result.Failure(DataError.Validation)
        }
        return try {
            val filterQuery = Filters.eq(QuizTopicEntity::_id.name, ObjectId(id))
            val deleteResult = topicCollection.deleteOne(filter = filterQuery)

            if (deleteResult.deletedCount > 0) {
                Result.Success(Unit)
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Validation)
        }
    }

    override suspend fun getAllTopics(): Result<List<QuizTopic>, DataError> {
        return try {
            val sortQuery = Sorts.ascending(QuizTopicEntity::code.name)
            val topics = topicCollection
                .find()
                .sort(sortQuery)
                .map { it.toQuizTopic() }
                .toList()

            if (topics.isNotEmpty()) {
                Result.Success(topics)
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Validation)
        }
    }

    override suspend fun getTopicById(id: String?): Result<QuizTopic, DataError> {
        if (id.isNullOrBlank()) {
            return Result.Failure(DataError.Validation)
        }
        return try {
            val filterQuery = Filters.eq(QuizTopicEntity::_id.name, ObjectId(id))
            val topic = topicCollection.find(filter = filterQuery).firstOrNull()

            if (topic != null) {
                Result.Success(topic.toQuizTopic())
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Validation)
        }
    }

    override suspend fun upsertTopic(topic: QuizTopic): Result<Unit, DataError> {
        return try {
            if (topic.id == null) {
                topicCollection.insertOne(topic.toQuizTopicEntity())
            } else {
                val filterQuery = Filters.eq(QuizTopicEntity::_id.name, ObjectId(topic.id))
                val updateQuery =
                    Updates.combine(
                        Updates.set(QuizTopicEntity::name.name, topic.name),
                        Updates.set(QuizTopicEntity::imageUrl.name, topic.imageUrl),
                        Updates.set(QuizTopicEntity::code.name, topic.code),
                    )
                val updatedResult = topicCollection.updateOne(filter = filterQuery, update = updateQuery)
                if (updatedResult.modifiedCount == 0L) {
                    return Result.Failure(DataError.NotFound)
                }
            }
            Result.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }
}