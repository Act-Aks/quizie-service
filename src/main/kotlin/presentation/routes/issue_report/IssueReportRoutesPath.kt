package com.actaks.presentation.routes.issue_report

import io.ktor.resources.*

@Resource("/report/issues")
class IssueReportRoutesPath {
    @Resource("{id}")
    data class ById(
        val parent: IssueReportRoutesPath = IssueReportRoutesPath(),
        val id: String,
    )

}