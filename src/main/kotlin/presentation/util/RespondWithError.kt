package com.actaks.presentation.util

import com.actaks.domain.util.DataError
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

suspend fun RoutingContext.respondWithError(
    error: DataError
) {
    when (error) {
        DataError.Database -> call.respond(
            message = "An unknown error occurred",
            status = HttpStatusCode.InternalServerError
        )

        DataError.NotFound -> call.respond(
            message = "Question not found",
            status = HttpStatusCode.NotFound
        )

        DataError.Unknown -> call.respond(
            message = "An unknown error occurred",
            status = HttpStatusCode.InternalServerError
        )

        DataError.Validation -> call.respond(
            message = "Invalid input",
            status = HttpStatusCode.BadRequest
        )
    }
}