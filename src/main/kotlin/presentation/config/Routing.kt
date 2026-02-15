package com.actaks.presentation.config

import com.actaks.domain.repository.IssueReportRepository
import com.actaks.domain.repository.QuizQuestionRepository
import com.actaks.domain.repository.QuizTopicRepository
import com.actaks.presentation.routes.issueReportRoutes
import com.actaks.presentation.routes.quizQuestionRoutes
import com.actaks.presentation.routes.quizTopicRoutes
import com.actaks.presentation.routes.root
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    install(Resources)

    val quizQuestionRepository: QuizQuestionRepository by inject()
    val quizTopicRepository: QuizTopicRepository by inject()
    val issueReportRepository: IssueReportRepository by inject()

    routing {
        root()
        quizQuestionRoutes(quizQuestionRepository)
        quizTopicRoutes(quizTopicRepository)
        issueReportRoutes(issueReportRepository)

        /* Static images routes */
        staticResources(
            remotePath = "/images",
            basePackage = "images"
        )
    }
}

