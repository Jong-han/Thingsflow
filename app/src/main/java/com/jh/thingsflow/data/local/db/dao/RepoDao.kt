package com.jh.thingsflow.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jh.thingsflow.data.local.db.entity.RepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repoEntity: RepoEntity)

    @Query("SELECT * FROM Repo WHERE org = :org AND repo = :repo")
    fun getRepoAsFlow(org: String, repo: String): Flow<RepoEntity?>

    @Query("SELECT * FROM Repo WHERE org = :org AND repo = :repo")
    fun getRepo(org: String, repo: String): RepoEntity?
}