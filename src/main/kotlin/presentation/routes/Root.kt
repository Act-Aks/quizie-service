package com.actaks.presentation.routes

import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.root() {
    get(path = "/") {
        call.respondText(
            text = "Welcome to Quizie API!",
        )
    }
}