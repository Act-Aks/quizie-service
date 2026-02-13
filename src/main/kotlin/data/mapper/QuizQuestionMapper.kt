package com.actaks.data.mapper

import com.actaks.data.database.entity.QuizQuestionEntity
import com.actaks.domain.model.QuizQuestion

fun QuizQuestionEntity.toQuizQuestion() = QuizQuestion(
    id = _id,
    question = question,
    rightAnswer = rightAnswer,
    wrongAnswers = wrongAnswers,
    explanation = explanation,
    topicCode = topicCode,
)

fun QuizQuestion.toQuizQuestionEntity() = QuizQuestionEntity(
    question = question,
    rightAnswer = rightAnswer,
    wrongAnswers = wrongAnswers,
    explanation = explanation,
    topicCode = topicCode,
)