package com.actaks.presentation.config

import com.actaks.data.database.DatabaseFactory
import com.actaks.data.repository.QuizQuestionRepositoryImpl
import com.actaks.domain.repository.QuizQuestionRepository
import com.actaks.presentation.routes.quiz_question.deleteQuizQuestionById
import com.actaks.presentation.routes.quiz_question.getAllQuizQuestions
import com.actaks.presentation.routes.quiz_question.getQuizQuestionById
import com.actaks.presentation.routes.quiz_question.upsertQuizQuestion
import com.actaks.presentation.routes.root
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val database = DatabaseFactory.create()
    val quizQuestionRepository: QuizQuestionRepository = QuizQuestionRepositoryImpl(database)

    routing {
        root()
        getAllQuizQuestions(quizQuestionRepository)
        getQuizQuestionById(quizQuestionRepository)
        upsertQuizQuestion(quizQuestionRepository)
        deleteQuizQuestionById(quizQuestionRepository)
    }
}

