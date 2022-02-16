package com.jh.thingsflow.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jh.thingsflow.data.local.db.dao.RepoDao
import com.jh.thingsflow.data.local.db.entity.RepoEntity
import com.jh.thingsflow.util.RepoConverter

@Database(entities = [RepoEntity::class], version = 1)
@TypeConverters(RepoConverter::class)
abstract class RepoDatabase: RoomDatabase() {
    abstract fun repoDao(): RepoDao
}