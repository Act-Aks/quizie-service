package com.actaks.presentation.routes.quiz_topic

import com.actaks.domain.repository.QuizTopicRepository
import com.actaks.domain.util.onFailure
import com.actaks.domain.util.onSuccess
import com.actaks.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteQuizTopicById(
    repository: QuizTopicRepository
) {
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
}