package com.actaks.data.mapper

import com.actaks.data.database.entity.QuizTopicEntity
import com.actaks.domain.model.QuizTopic

fun QuizTopicEntity.toQuizTopic() = QuizTopic(
    id = _id,
    name = name,
    imageUrl = imageUrl,
    code = code,
)

fun QuizTopic.toQuizTopicEntity() = QuizTopicEntity(
    name = name,
    imageUrl = imageUrl,
    code = code,
)