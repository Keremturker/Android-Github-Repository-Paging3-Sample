package com.turker.github_repository_paging3_sample.network

import com.turker.github_repository_paging3_sample.data.model.RepoResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface RepoService{
    @GET("/search/repositories?sort=stars")
    suspend fun searchRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): RepoResponse
}