package com.turker.github_repository_paging3_sample.data.repository

import androidx.paging.PagingData
import com.turker.github_repository_paging3_sample.data.model.RepoModel
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    fun getRepo(query: String): Flow<PagingData<RepoModel>>
}