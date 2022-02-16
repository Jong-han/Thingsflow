package com.jh.thingsflow.data.remote.datasource

import com.jh.thingsflow.data.Resource
import com.jh.thingsflow.data.remote.response.ResultIssue
import com.jh.thingsflow.data.remote.service.IssueService
import javax.inject.Inject

class IssueDataSourceImpl @Inject constructor(private val service: IssueService): IssueDataSource {
    override suspend fun getIssueList(org: String, repo: String): Resource<ResultIssue> {
        return kotlin.runCatching {
            val response = service.getIssue(org, repo)
            Resource.Success(response)
        }.onFailure {
            return Resource.Error(it.message)
        }.getOrThrow()
    }
}