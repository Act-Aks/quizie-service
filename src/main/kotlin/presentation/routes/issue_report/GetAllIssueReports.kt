package com.actaks.presentation.routes.issue_report

import com.actaks.domain.repository.IssueReportRepository
import com.actaks.domain.util.onFailure
import com.actaks.domain.util.onSuccess
import com.actaks.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAllIssueReports(
    repository: IssueReportRepository
) {
    get<IssueReportRoutesPath> {
        repository
            .getAllIssueReports()
            .onSuccess { reports ->
                call.respond(
                    message = reports,
                    status = HttpStatusCode.OK
                )
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }
}