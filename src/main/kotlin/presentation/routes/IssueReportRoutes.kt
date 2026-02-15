package com.actaks.presentation.routes

import com.actaks.domain.model.IssueReport
import com.actaks.domain.repository.IssueReportRepository
import com.actaks.domain.util.onFailure
import com.actaks.domain.util.onSuccess
import com.actaks.presentation.routes.path.IssueReportRoutesPath
import com.actaks.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.Route

fun Route.issueReportRoutes(
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

    delete<IssueReportRoutesPath.ById> { path ->
        repository
            .deleteIssueReportById(path.id)
            .onSuccess {
                call.respond(HttpStatusCode.NoContent)
            }
            .onFailure { error ->
                respondWithError(error)
            }
    }

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