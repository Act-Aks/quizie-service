package com.actaks.domain.repository

import com.actaks.domain.model.QuizQuestion
import com.actaks.domain.util.DataError
import com.actaks.domain.util.Result

interface QuizQuestionRepository {
    suspend fun upsertQuestion(
        question: QuizQuestion
    ): Result<Unit, DataError>

    suspend fun getAllQuestions(
        topicCode: Int?, limit: Int?
    ): Result<List<QuizQuestion>, DataError>

    suspend fun getQuestionById(
        id: String?
    ): Result<QuizQuestion, DataError>

    suspend fun deleteQuestion(
        id: String?
    ): Result<Unit, DataError>
}