package com.jh.thingsflow.data.local.db.entity

import androidx.room.Entity
import com.jh.thingsflow.data.remote.response.ResultIssue

@Entity(tableName = "Repo", primaryKeys = ["org","repo"])
data class RepoEntity (
    val org: String,
    val repo: String,
    val issues: ResultIssue
    )