package com.jh.thingsflow.usecase

import com.jh.thingsflow.base.BaseModel
import com.jh.thingsflow.data.Resource
import com.jh.thingsflow.data.remote.response.ResultIssue
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
        return localRepository.getRepoAsFlow(org, repo).map {
            it?.let { repoEntity ->
                repoEntity.issues.flatMapIndexed { index: Int, resultIssueItem: BaseModel.ResultIssueItem ->
                    if (index != 3)
                        listOf(resultIssueItem)
                    else
                        listOf(resultIssueItem, BaseModel.BannerModel())
                }
            } ?: emptyList()
        }
    }

    override suspend fun isExist(org: String, repo: String): Boolean {
        return localRepository.isExist(org, repo)
    }

}