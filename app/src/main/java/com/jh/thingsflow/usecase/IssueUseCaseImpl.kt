package com.jh.thingsflow.usecase

import com.jh.thingsflow.data.Resource
import com.jh.thingsflow.data.remote.response.ResultIssue
import com.jh.thingsflow.data.repository.IssueRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IssueUseCaseImpl @Inject constructor(private val issueRepository: IssueRepository): IssueUseCase {
    override fun getIssueListFromRemote(org: String, repo: String): Flow<Resource<ResultIssue>> = issueRepository.getIssueList(org, repo)
}