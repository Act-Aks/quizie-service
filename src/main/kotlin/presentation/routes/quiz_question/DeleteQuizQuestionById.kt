package com.actaks.presentation.routes.quiz_question

import com.actaks.domain.repository.QuizQuestionRepository
import com.actaks.domain.util.onFailure
import com.actaks.domain.util.onSuccess
import com.actaks.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteQuizQuestionById(
    repository: QuizQuestionRepository
) {
    delete(path = "/quiz/questions/{id}") {
        val id = call.parameters["id"]
        repository
            .deleteQuestion(id)
            .onSuccess {
                call.respond(HttpStatusCode.NoContent)
            }.onFailure { error ->
                respondWithError(error)
            }
    }
}