package com.actaks.presentation.routes.path

import io.ktor.resources.*

@Resource("/quiz/questions")
class QuizQuestionRoutesPath(
    val topicCode: Int? = null,
) {
    @Resource("{id}")
    data class ById(
        val parent: QuizQuestionRoutesPath = QuizQuestionRoutesPath(),
        val id: String,
    )

    @Resource("bulk")
    data class Bulk(
        val parent: QuizQuestionRoutesPath = QuizQuestionRoutesPath(),
    )

    @Resource("random")
    data class Random(
        val parent: QuizQuestionRoutesPath = QuizQuestionRoutesPath(),
        val topicCode: Int? = null,
        val limit: Int? = null,
    )
}