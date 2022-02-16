package com.jh.thingsflow.usecase

import com.jh.thingsflow.base.BaseModel
import com.jh.thingsflow.data.BannerModel
import com.jh.thingsflow.data.Resource
import com.jh.thingsflow.data.remote.response.ResultIssue
import com.jh.thingsflow.data.remote.response.ResultIssueItem
import com.jh.thingsflow.data.repository.IssueRepository
import com.jh.thingsflow.data.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IssueUseCaseImpl @Inject constructor(private val issueRepository: IssueRepository,
                                           private val localRepository: LocalRepository): IssueUseCase {
    override fun getIssueListFromRemote(org: String, repo: String): Flow<Resource<ResultIssue>> = issueRepository.getIssueList(org, repo)

    override suspend fun insertRepo(org: String, repo: String, resultIssue: ResultIssue) {
        localRepository.insertRepo(org, repo, resultIssue)
    }

    override suspend fun getRepo(org: String, repo: String): Flow<List<BaseModel>> {
        return localRepository.getRepo(org, repo).map {
            it.issues.flatMapIndexed { index: Int, resultIssueItem: ResultIssueItem ->
                if (index != 4)
                    listOf(resultIssueItem)
                else
                    listOf(resultIssueItem, BannerModel())
            }
        }
    }


}