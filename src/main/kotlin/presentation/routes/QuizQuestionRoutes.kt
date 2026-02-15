package com.actaks.presentation.routes

import com.actaks.domain.model.QuizQuestion
import com.actaks.domain.repository.QuizQuestionRepository
import com.actaks.domain.util.onFailure
import com.actaks.domain.util.onSuccess
import com.actaks.presentation.routes.path.QuizQuestionRoutesPath
import com.actaks.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route

fun Route.quizQuestionRoutes(
    repository: QuizQuestionRepository
) {
    get<QuizQuestionRoutesPath> { path ->
        repository
            .getAllQuestions(path.topicCode)
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

    get<QuizQuestionRoutesPath.ById> { path ->
        repository
            .getQuestionById(path.id)
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

    get<QuizQuestionRoutesPath.Random> { path ->
        repository
            .getRandomQuestions(path.topicCode, path.limit)
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

    delete<QuizQuestionRoutesPath.ById> { path ->
        repository
            .deleteQuestion(path.id)
            .onSuccess {
                call.respond(HttpStatusCode.NoContent)
            }.onFailure { error ->
                respondWithError(error)
            }
    }

    post<QuizQuestionRoutesPath.Bulk> {
        val questions = call.receive<List<QuizQuestion>>()
        repository
            .insertQuestionsInBulk(questions)
            .onSuccess {
                call.respond(
                    message = "Questions added successfully!",
                    status = HttpStatusCode.Created
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }

    post<QuizQuestionRoutesPath> {
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