package com.jh.thingsflow.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jh.thingsflow.data.remote.response.ResultIssue
import com.jh.thingsflow.usecase.IssueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val issueUseCase: IssueUseCase, private val iODispatcher: CoroutineDispatcher): ViewModel() {

    var org = "google"
    var repo = "dagger"

    fun getIssueList() = issueUseCase.getIssueListFromRemote(org, repo)

    fun insertRepo(resultIssue: ResultIssue) {
        viewModelScope.launch(iODispatcher) {
            issueUseCase.insertRepo(org, repo, resultIssue)
        }
    }

    suspend fun getIssueListFromLocal() = withContext(iODispatcher) {
        issueUseCase.getRepo(org, repo)
    }
}