package com.jh.thingsflow.di

import android.content.Context
import androidx.room.Room
import com.jh.thingsflow.data.local.db.RepoDatabase
import com.jh.thingsflow.data.repository.LocalRepository
import com.jh.thingsflow.data.repository.LocalRepositoryImpl
import com.jh.thingsflow.util.EntityMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalProvidesModule {
    @Provides
    @Singleton
    fun providesRepoDatabase(@ApplicationContext context: Context): RepoDatabase {
        return Room.databaseBuilder(context, RepoDatabase::class.java, "RepoDatabase.db").build()
    }

    @Provides
    @Singleton
    fun providesRepoDao(repoDatabase: RepoDatabase) = repoDatabase.repoDao()

    @Provides
    fun providesMapper() = EntityMapper()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalBindsModule {
    @Binds
    @Singleton
    abstract fun bindsLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository
}