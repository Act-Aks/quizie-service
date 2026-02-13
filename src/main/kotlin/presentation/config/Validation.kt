package com.actaks.presentation.config

import com.actaks.presentation.validator.validateQuizQuestion
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureValidation() {
    install(RequestValidation) {
        validateQuizQuestion()
    }
}