package com.turker.github_repository_paging3_sample.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.turker.github_repository_paging3_sample.data.model.RepoModel
import com.turker.github_repository_paging3_sample.data.pagingdatasource.RepoPagingDataSource
import com.turker.github_repository_paging3_sample.network.APIClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RepoRepositoryImpl @Inject constructor(
    private val repoService: APIClient
) : RepoRepository {
    override fun getRepo(query: String): Flow<PagingData<RepoModel>> {
        return Pager(config = PagingConfig(pageSize = NETWORK_PAGE_SIZE), pagingSourceFactory = {
            RepoPagingDataSource(repoService.apiCollect, query)
        }).flow
    }


    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}