package com.actaks.presentation.routes.quiz_topic

import io.ktor.resources.*

@Resource("/quiz/topics")
class QuizTopicRoutesPath {
    @Resource("{id}")
    data class ById(
        val parent: QuizTopicRoutesPath = QuizTopicRoutesPath(),
        val id: String,
    )
}