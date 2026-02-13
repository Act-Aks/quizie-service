package com.actaks.data.database.entity

import org.bson.BsonType
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonRepresentation
import org.bson.types.ObjectId

data class IssueReportEntity(
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    val _id: String = ObjectId().toString(),
    val questionId: String,
    val issueType: String,
    val comment: String? = null,
    val userEmail: String? = null,
    val timestamp: String,
)
