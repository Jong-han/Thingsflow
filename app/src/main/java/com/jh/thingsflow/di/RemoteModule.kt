package com.jh.thingsflow.di

import com.jh.thingsflow.data.remote.RetrofitCreator
import com.jh.thingsflow.data.remote.datasource.IssueDataSource
import com.jh.thingsflow.data.remote.datasource.IssueDataSourceImpl
import com.jh.thingsflow.data.remote.service.IssueService
import com.jh.thingsflow.data.repository.IssueRepository
import com.jh.thingsflow.data.repository.IssueRepositoryImpl
import com.jh.thingsflow.usecase.IssueUseCase
import com.jh.thingsflow.usecase.IssueUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteProvidesModule {
    @Provides
    @Singleton
    fun providesIssueService(): IssueService {
        return RetrofitCreator.createRetrofit().create(IssueService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteBindsModule {
    @Binds
    @Singleton
    abstract fun bindsIssueDataSource(issueDataSourceImpl: IssueDataSourceImpl): IssueDataSource

    @Binds
    @Singleton
    abstract fun bindsIssueRepository(issueRepositoryImpl: IssueRepositoryImpl): IssueRepository

    @Binds
    @Singleton
    abstract fun bindsIssueUseCase(issueUseCaseImpl: IssueUseCaseImpl): IssueUseCase
}