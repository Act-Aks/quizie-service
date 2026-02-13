package com.actaks.data.mapper

import com.actaks.data.database.entity.IssueReportEntity
import com.actaks.domain.model.IssueReport

fun IssueReportEntity.toIssueReport() = IssueReport(
    id = _id,
    questionId = questionId,
    issueType = issueType,
    comment = comment,
    userEmail = userEmail,
    timestamp = timestamp
)

fun IssueReport.toIssueReportEntity() = IssueReportEntity(
    questionId = questionId,
    issueType = issueType,
    comment = comment,
    userEmail = userEmail,
    timestamp = timestamp
)