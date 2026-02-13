package com.actaks.presentation.routes.quiz_question

import com.actaks.domain.model.QuizQuestion
import com.actaks.domain.repository.QuizQuestionRepository
import com.actaks.domain.util.onFailure
import com.actaks.domain.util.onSuccess
import com.actaks.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.upsertQuizQuestion(
    repository: QuizQuestionRepository,
) {
    post(path = "/quiz/questions") {
        val question = call.receive<QuizQuestion>()
        repository
            .upsertQuestion(question)
            .onSuccess {
                call.respond(
                    message = if (question.id == null) "Question added successfully" else "Question updated successfully",
                    status = HttpStatusCode.Created
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }

    }
}