package com.turker.github_repository_paging3_sample.ui

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.turker.github_repository_paging3_sample.base.BaseViewModel
import com.turker.github_repository_paging3_sample.data.model.RepoModel
import com.turker.github_repository_paging3_sample.data.repository.RepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    myApp: Application,
    private val repoRepository: RepoRepository
) : BaseViewModel(app = myApp) {

    fun callRepo(query: String): Flow<PagingData<RepoModel>> {
        val repoItemsUiStates = repoRepository.getRepo(query)
            .map { pagingData ->
                pagingData.map { repoModel -> repoModel }
            }.cachedIn(viewModelScope)
        return repoItemsUiStates
    }

}
