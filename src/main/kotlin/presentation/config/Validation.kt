package com.actaks.presentation.config

import com.actaks.presentation.validator.validateIssueReport
import com.actaks.presentation.validator.validateQuizQuestion
import com.actaks.presentation.validator.validateQuizTopic
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureValidation() {
    install(RequestValidation) {
        validateQuizQuestion()
        validateQuizTopic()
        validateIssueReport()
    }
}