package com.actaks.data.repository

import com.actaks.data.database.entity.QuizQuestionEntity
import com.actaks.data.mapper.toQuizQuestion
import com.actaks.data.mapper.toQuizQuestionEntity
import com.actaks.data.util.Constant.QUESTIONS_COLLECTION_NAME
import com.actaks.domain.model.QuizQuestion
import com.actaks.domain.repository.QuizQuestionRepository
import com.actaks.domain.util.DataError
import com.actaks.domain.util.Result
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId

class QuizQuestionRepositoryImpl(
    database: MongoDatabase
) : QuizQuestionRepository {

    private val questionCollection = database
        .getCollection<QuizQuestionEntity>(QUESTIONS_COLLECTION_NAME)

    override suspend fun upsertQuestion(question: QuizQuestion): Result<Unit, DataError> {
        return try {
            if (question.id == null) {
                questionCollection.insertOne(question.toQuizQuestionEntity())
            } else {
                val filterQuery = Filters.eq(QuizQuestionEntity::_id.name, ObjectId(question.id))
                val updateQuery =
                    Updates.combine(
                        Updates.set(QuizQuestionEntity::question.name, question.question),
                        Updates.set(QuizQuestionEntity::rightAnswer.name, question.rightAnswer),
                        Updates.set(QuizQuestionEntity::wrongAnswers.name, question.wrongAnswers),
                        Updates.set(QuizQuestionEntity::explanation.name, question.explanation),
                        Updates.set(QuizQuestionEntity::topicCode.name, question.topicCode),
                    )
                val updatedResult = questionCollection.updateOne(filter = filterQuery, update = updateQuery)
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

    override suspend fun getAllQuestions(
        topicCode: Int?,
        limit: Int?
    ): Result<List<QuizQuestion>, DataError> {
        return try {
            val questionsLimit = limit?.takeIf { it > 0 } ?: 10
            val filterQuery = topicCode?.let { Filters.eq(QuizQuestionEntity::topicCode.name, it) }
                ?: Filters.empty()

            val questions = questionCollection
                .find(filter = filterQuery)
                .take(questionsLimit)
                .map { it.toQuizQuestion() }
                .toList()

            if (questions.isNotEmpty()) {
                Result.Success(questions)
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun getQuestionById(id: String?): Result<QuizQuestion, DataError> {
        if (id.isNullOrBlank()) {
            return Result.Failure(DataError.Validation)
        }
        return try {
            val filterQuery = Filters.eq(QuizQuestionEntity::_id.name, ObjectId(id))
            val questionEntity = questionCollection.find(filter = filterQuery).firstOrNull()

            if (questionEntity != null) {
                Result.Success(questionEntity.toQuizQuestion())
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Failure(DataError.Database)
        }
    }

    override suspend fun deleteQuestion(id: String?): Result<Unit, DataError> {
        if (id.isNullOrBlank()) {
            return Result.Failure(DataError.Validation)
        }
        return try {
            val filterQuery = Filters.eq(QuizQuestionEntity::_id.name, ObjectId(id))
            val deleteResult = questionCollection.deleteOne(filter = filterQuery)

            if (deleteResult.deletedCount > 0) {
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