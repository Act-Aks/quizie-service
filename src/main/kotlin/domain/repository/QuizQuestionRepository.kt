package com.actaks.domain.repository

import com.actaks.domain.model.QuizQuestion
import com.actaks.domain.util.DataError
import com.actaks.domain.util.Result

interface QuizQuestionRepository {
    suspend fun getAllQuestions(topicCode: Int?): Result<List<QuizQuestion>, DataError>
    
    suspend fun getRandomQuestions(topicCode: Int?, limit: Int?): Result<List<QuizQuestion>, DataError>

    suspend fun getQuestionById(id: String?): Result<QuizQuestion, DataError>

    suspend fun deleteQuestion(id: String?): Result<Unit, DataError>

    suspend fun insertQuestionsInBulk(questions: List<QuizQuestion>): Result<Unit, DataError>

    suspend fun upsertQuestion(question: QuizQuestion): Result<Unit, DataError>

}