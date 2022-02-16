package com.jh.thingsflow.data.repository

import com.jh.thingsflow.data.local.db.dao.RepoDao
import com.jh.thingsflow.data.local.db.entity.RepoEntity
import com.jh.thingsflow.data.remote.response.ResultIssue
import com.jh.thingsflow.util.EntityMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val repoDao: RepoDao, private val mapper: EntityMapper): LocalRepository {

    override suspend fun insertRepo(org: String, repo: String, resultIssue: ResultIssue) {
        val repoEntity = mapper.mappingIssueToRepo(org, repo, resultIssue)
        repoDao.insert(repoEntity)
    }

    override suspend fun getRepo(org: String, repo: String): Flow<RepoEntity> {
        return repoDao.getRepo(org, repo)
    }
}