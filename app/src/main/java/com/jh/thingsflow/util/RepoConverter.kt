package com.jh.thingsflow.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.jh.thingsflow.data.remote.response.ResultIssue

class RepoConverter {
    @TypeConverter
    fun repoToJson(value: ResultIssue): String = Gson().toJson(value)
    @TypeConverter
    fun jsonToRepo(value: String): ResultIssue = Gson().fromJson(value, ResultIssue::class.java)
}