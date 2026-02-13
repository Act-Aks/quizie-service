package com.actaks.presentation.config

import com.actaks.domain.repository.IssueReportRepository
import com.actaks.domain.repository.QuizQuestionRepository
import com.actaks.domain.repository.QuizTopicRepository
import com.actaks.presentation.routes.issue_report.deleteIssueReportById
import com.actaks.presentation.routes.issue_report.getAllIssueReports
import com.actaks.presentation.routes.issue_report.insertIssueReport
import com.actaks.presentation.routes.quiz_question.deleteQuizQuestionById
import com.actaks.presentation.routes.quiz_question.getAllQuizQuestions
import com.actaks.presentation.routes.quiz_question.getQuizQuestionById
import com.actaks.presentation.routes.quiz_question.upsertQuizQuestion
import com.actaks.presentation.routes.quiz_topic.deleteQuizTopicById
import com.actaks.presentation.routes.quiz_topic.getAllQuizTopics
import com.actaks.presentation.routes.quiz_topic.getQuizTopicById
import com.actaks.presentation.routes.quiz_topic.upsertQuizTopic
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

        /* Quiz questions routes */
        getAllQuizQuestions(quizQuestionRepository)
        getQuizQuestionById(quizQuestionRepository)
        deleteQuizQuestionById(quizQuestionRepository)
        upsertQuizQuestion(quizQuestionRepository)

        /* Quiz topics routes */
        getAllQuizTopics(quizTopicRepository)
        getQuizTopicById(quizTopicRepository)
        deleteQuizTopicById(quizTopicRepository)
        upsertQuizTopic(quizTopicRepository)

        /* Issue reports routes */
        getAllIssueReports(issueReportRepository)
        deleteIssueReportById(issueReportRepository)
        insertIssueReport(issueReportRepository)

        /* Static images routes */
        staticResources(
            remotePath = "/images",
            basePackage = "images"
        )
    }
}

