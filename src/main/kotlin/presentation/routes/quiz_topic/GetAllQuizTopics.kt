package com.actaks.presentation.routes.quiz_topic

import com.actaks.domain.repository.QuizTopicRepository
import com.actaks.domain.util.onFailure
import com.actaks.domain.util.onSuccess
import com.actaks.presentation.util.respondWithError
import io.ktor.server.routing.*

fun Route.getAllQuizTopic(
    quizTopicRepository: QuizTopicRepository
) {
    get<QuizTopicRoutesPath> {
        quizTopicRepository.getAllTopics()
            .onSuccess { topics ->
                call.respond(
                    message = topics,
                    stat
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}