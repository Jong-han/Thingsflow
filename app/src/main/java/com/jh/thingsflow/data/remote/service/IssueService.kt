package com.jh.thingsflow.data.remote.service

import com.jh.thingsflow.data.remote.response.ResultIssue
import retrofit2.http.GET
import retrofit2.http.Path

interface IssueService {
    @GET("repos/{org}/{repo}/issues")
    suspend fun getIssue(@Path("org") org: String, @Path("repo") repo: String): ResultIssue
}