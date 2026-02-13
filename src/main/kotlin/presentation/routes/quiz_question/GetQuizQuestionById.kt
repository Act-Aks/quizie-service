package com.actaks.presentation.routes.quiz_question

import com.actaks.domain.repository.QuizQuestionRepository
import com.actaks.domain.util.onFailure
import com.actaks.domain.util.onSuccess
import com.actaks.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getQuizQuestionById(
    repository: QuizQuestionRepository
) {
    get(path = "/quiz/questions/{id}") {
        val id = call.parameters["id"]
        repository
            .getQuestionById(id)
            .onSuccess { question ->
                call.respond(
                    message = question,
                    status = HttpStatusCode.OK
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}