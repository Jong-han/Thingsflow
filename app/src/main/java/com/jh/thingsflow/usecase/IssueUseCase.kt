package com.jh.thingsflow.usecase

import com.jh.thingsflow.data.Resource
import com.jh.thingsflow.data.remote.response.ResultIssue
import kotlinx.coroutines.flow.Flow

interface IssueUseCase {
    fun getIssueListFromRemote(org: String, repo: String): Flow<Resource<ResultIssue>>
}