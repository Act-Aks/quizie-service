package com.actaks.data.database.entity

import org.bson.BsonType
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonRepresentation
import org.bson.types.ObjectId

data class QuizTopicEntity(
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    val _id: String = ObjectId().toString(),
    val name: String,
    val imageUrl: String,
    val code: Int
)
