package com.actaks.presentation.routes

import com.actaks.domain.model.QuizTopic
import com.actaks.domain.repository.QuizTopicRepository
import com.actaks.domain.util.onFailure
import com.actaks.domain.util.onSuccess
import com.actaks.presentation.routes.path.QuizTopicRoutesPath
import com.actaks.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route

fun Route.quizTopicRoutes(
    repository: QuizTopicRepository
) {
    get<QuizTopicRoutesPath> {
        repository.getAllTopics()
            .onSuccess { topics ->
                call.respond(
                    message = topics,
                    status = HttpStatusCode.OK
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }

    get<QuizTopicRoutesPath.ById> { path ->
        repository
            .getTopicById(path.id)
            .onSuccess { topic ->
                call.respond(
                    message = topic,
                    status = HttpStatusCode.OK
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }

    delete<QuizTopicRoutesPath.ById> { path ->
        repository
            .deleteTopic(path.id)
            .onSuccess {
                call.respond(HttpStatusCode.NoContent)
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }

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