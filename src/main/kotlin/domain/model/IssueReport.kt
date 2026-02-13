package com.actaks.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class IssueReport(
    val id: String? = null,
    val questionId: String,
    val issueType: String,
    val comment: String? = null,
    val userEmail: String? = null,
    val timestamp: String,
)
