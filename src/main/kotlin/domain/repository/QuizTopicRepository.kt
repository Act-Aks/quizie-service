package com.actaks.domain.repository

import com.actaks.domain.model.QuizTopic
import com.actaks.domain.util.DataError
import com.actaks.domain.util.Result

interface QuizTopicRepository {
    suspend fun deleteTopic(
        id: String?
    ): Result<Unit, DataError>

    suspend fun getAllTopics(): Result<List<QuizTopic>, DataError>
    suspend fun getTopicById(
        id: String?
    ): Result<QuizTopic, DataError>

    suspend fun upsertTopic(
        topic: QuizTopic
    ): Result<Unit, DataError>
}