package com.jh.thingsflow.data.remote.datasource

import com.jh.thingsflow.data.Resource
import com.jh.thingsflow.data.remote.response.ResultIssue

interface IssueDataSource {
    suspend fun getIssueList(org: String, repo: String): Resource<ResultIssue>
}