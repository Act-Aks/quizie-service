package com.actaks.presentation.routes.quiz_topic

import com.actaks.domain.model.QuizTopic
import com.actaks.domain.repository.QuizTopicRepository
import com.actaks.domain.util.onFailure
import com.actaks.domain.util.onSuccess
import com.actaks.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route

fun Route.upsertQuizTopic(
    repository: QuizTopicRepository
) {
    post<QuizTopicRoutesPath> {
        val topic = call.receive<QuizTopic>()
        repository
            .upsertTopic(topic)
            .onSuccess {
                call.respond(
                    message = if (topic.id == null) "Topic added successfully" else "Topic updated successfully",
                    status = HttpStatusCode.Created
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}