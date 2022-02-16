package com.jh.thingsflow.data.repository

import com.jh.thingsflow.data.Resource
import com.jh.thingsflow.data.remote.datasource.IssueDataSource
import com.jh.thingsflow.data.remote.response.ResultIssue
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class IssueRepositoryImpl @Inject constructor(private val dataSource: IssueDataSource, private val coroutineDispatcher: CoroutineDispatcher): IssueRepository {
    override fun getIssueList(org: String, repo: String): Flow<Resource<ResultIssue>> = flow {
        emit(Resource.Loading())
        emit(dataSource.getIssueList(org, repo))
    }.flowOn(coroutineDispatcher)
}