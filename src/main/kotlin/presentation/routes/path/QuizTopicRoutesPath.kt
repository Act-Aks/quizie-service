package com.actaks.presentation.routes.path

import io.ktor.resources.*

@Resource("/quiz/topics")
class QuizTopicRoutesPath {
    @Resource("{id}")
    data class ById(
        val parent: QuizTopicRoutesPath = QuizTopicRoutesPath(),
        val id: String,
    )
}