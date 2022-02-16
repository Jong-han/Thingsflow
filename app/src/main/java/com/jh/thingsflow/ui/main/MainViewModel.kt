package com.jh.thingsflow.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jh.thingsflow.data.Resource
import com.jh.thingsflow.data.remote.response.ResultIssue
import com.jh.thingsflow.usecase.IssueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val issueUseCase: IssueUseCase, private val iODispatcher: CoroutineDispatcher): ViewModel() {

//    var org = "google"
//    var repo = "dagger"

    sealed class ViewEvent {
        object ClickTitle: ViewEvent()
    }

    private val _org = MutableStateFlow("google")
    val org: StateFlow<String> = _org

    private val _repo = MutableStateFlow("dagger")
    val repo: StateFlow<String> = _repo

    private val _viewEvent = MutableSharedFlow<ViewEvent>()
    val viewEvent: SharedFlow<ViewEvent> = _viewEvent

    fun onClickTitle() {
        viewModelScope.launch {
            _viewEvent.emit(ViewEvent.ClickTitle)
        }
    }

    fun getIssueListFromRemote() = issueUseCase.getIssueListFromRemote(_org.value, _repo.value)

    fun insertRepo(org: String, repo: String, resultIssue: ResultIssue) {
        viewModelScope.launch(iODispatcher) {
            issueUseCase.insertRepo(org, repo, resultIssue)
        }
    }

    suspend fun getIssueListFromLocal() = withContext(iODispatcher) {
        issueUseCase.getRepo(_org.value, _repo.value)
    }

    fun searchRepo(targetOrg: String, targetRepo: String) {
        /**
         * db에서 있나 없나 확인
         * db에서 검색
         * api 검색
         */
        viewModelScope.launch {
            CoroutineScope(iODispatcher).launch {
                if (!issueUseCase.isExist(targetOrg, targetRepo)) {
                    getIssueListFromRemote().collect { resource ->
                        when (resource) {
                            is Resource.Loading -> {
                                Log.i("asdf","loading")
                            }
                            is Resource.Success -> {
                                val data = resource.data
                                data?.let { insertRepo(targetOrg, targetRepo, resource.data) }
                                Log.i("asdf","success")
                            }
                            is Resource.Error -> {
                                Log.i("asdf","error")
                            }
                        }
                    }
                }
            }.join()
        }
    }

    fun updateOrgAndRepo(org: String, repo: String ) {
        this._org.value = org
        this._repo.value = repo
    }
}