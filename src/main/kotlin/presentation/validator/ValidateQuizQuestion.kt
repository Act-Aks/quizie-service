package com.actaks.presentation.validator

import com.actaks.domain.model.QuizQuestion
import io.ktor.server.plugins.requestvalidation.*

fun RequestValidationConfig.validateQuizQuestion() {
    validate<QuizQuestion> { quizQuestion ->
        when {
            quizQuestion.question.isBlank() || quizQuestion.question.length < 5 -> ValidationResult.Invalid(
                reason = "Question must be at least 5 characters long and not empty"
            )

            quizQuestion.rightAnswer.isBlank() -> ValidationResult.Invalid(
                reason = "Right answer must not be empty"
            )

            quizQuestion.wrongAnswers.isEmpty() -> ValidationResult.Invalid(
                reason = "There must be at least one wrong answer"
            )

            quizQuestion.wrongAnswers.any { it.isBlank() } -> ValidationResult.Invalid(
                reason = "Wrong answers must not be empty"
            )

            quizQuestion.explanation.isBlank() -> ValidationResult.Invalid(
                reason = "Explanation must not be empty"
            )

            quizQuestion.topicCode <= 0 -> ValidationResult.Invalid(
                reason = "Topic code must be a positive integer"
            )

            else -> ValidationResult.Valid
        }
    }
}
