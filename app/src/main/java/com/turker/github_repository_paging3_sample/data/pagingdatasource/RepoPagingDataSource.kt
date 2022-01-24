package com.turker.github_repository_paging3_sample.data.pagingdatasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.turker.github_repository_paging3_sample.data.model.RepoModel
import com.turker.github_repository_paging3_sample.network.RepoService


class RepoPagingDataSource(
    private val repoService: RepoService,
    private val query: String
) :
    PagingSource<Int, RepoModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoModel> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = repoService.searchRepos(query,page, params.loadSize)
            LoadResult.Page(
                data = response.items,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.items.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, RepoModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

}
