package com.jh.thingsflow.data.repository

import com.jh.thingsflow.data.Resource
import com.jh.thingsflow.data.remote.response.ResultIssue
import kotlinx.coroutines.flow.Flow

interface IssueRepository {
    fun getIssueList(org: String, repo: String): Flow<Resource<ResultIssue>>
}