package com.actaks.presentation.routes.issue_report

import com.actaks.domain.model.IssueReport
import com.actaks.domain.repository.IssueReportRepository
import com.actaks.domain.util.onFailure
import com.actaks.domain.util.onSuccess
import com.actaks.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route

fun Route.insertIssueReport(
    repository: IssueReportRepository
) {
    post<IssueReportRoutesPath> {
        val issueReport = call.receive<IssueReport>()
        repository
            .insertIssueReport(issueReport)
            .onSuccess {
                call.respond(
                    message = "Issue created successfully",
                    status = HttpStatusCode.Created
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}