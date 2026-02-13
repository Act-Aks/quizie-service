package com.actaks.presentation.routes.issue_report

import com.actaks.domain.repository.IssueReportRepository
import com.actaks.domain.util.onFailure
import com.actaks.domain.util.onSuccess
import com.actaks.presentation.util.respondWithError
import io.ktor.http.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteIssueReportById(
    repository: IssueReportRepository
) {
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
}