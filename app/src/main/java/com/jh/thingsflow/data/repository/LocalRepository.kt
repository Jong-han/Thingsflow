package com.jh.thingsflow.data.repository

import com.jh.thingsflow.data.local.db.entity.RepoEntity
import com.jh.thingsflow.data.remote.response.ResultIssue
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun insertRepo(org: String, repo: String, resultIssue: ResultIssue)
    suspend fun getRepo(org: String, repo: String): Flow<RepoEntity>
}