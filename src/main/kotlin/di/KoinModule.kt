package com.actaks.di

import com.actaks.data.database.DatabaseFactory
import com.actaks.data.repository.IssueReportRepositoryImpl
import com.actaks.data.repository.QuizQuestionRepositoryImpl
import com.actaks.data.repository.QuizTopicRepositoryImpl
import com.actaks.domain.repository.IssueReportRepository
import com.actaks.domain.repository.QuizQuestionRepository
import com.actaks.domain.repository.QuizTopicRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val koinModule = module {
    single { DatabaseFactory.create() }
    singleOf(::QuizQuestionRepositoryImpl).bind<QuizQuestionRepository>()
    singleOf(::QuizTopicRepositoryImpl).bind<QuizTopicRepository>()
    singleOf(::IssueReportRepositoryImpl).bind<IssueReportRepository>()
}