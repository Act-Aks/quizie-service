package com.actaks.presentation.routes.quiz_question

import io.ktor.resources.*

@Resource("/quiz/questions")
class QuizQuestionRoutesPath(
    val topicCode: Int? = null,
    val limit: Int? = null,
) {
    @Resource("{id}")
    data class ById(
        val parent: QuizQuestionRoutesPath = QuizQuestionRoutesPath(),
        val id: String,
    )
}