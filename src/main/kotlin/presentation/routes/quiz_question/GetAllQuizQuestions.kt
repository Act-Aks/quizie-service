package com.actaks.presentation.routes.quiz_question

import com.actaks.domain.repository.QuizQuestionRepository
import com.actaks.domain.util.onFailure
import com.actaks.domain.util.onSuccess
import com.actaks.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAllQuizQuestions(
    repository: QuizQuestionRepository
) {
    get<QuizQuestionRoutesPath> { path ->
        repository
            .getAllQuestions(path.topicCode, path.limit)
            .onSuccess { questions ->
                call.respond(
                    message = questions,
                    status = HttpStatusCode.OK
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}