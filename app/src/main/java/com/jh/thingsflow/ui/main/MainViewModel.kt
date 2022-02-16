package com.jh.thingsflow.ui.main

import androidx.lifecycle.ViewModel
import com.jh.thingsflow.usecase.IssueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val issueUseCase: IssueUseCase): ViewModel() {
    fun test() = issueUseCase.getIssueListFromRemote("google","dagger")
}