package com.jh.thingsflow.util

import com.jh.thingsflow.data.local.db.entity.RepoEntity
import com.jh.thingsflow.data.remote.response.ResultIssue

class EntityMapper {
    fun mappingIssueToRepo(org: String, repo: String, resultIssue: ResultIssue): RepoEntity {
        return RepoEntity(org, repo, resultIssue)
    }
}