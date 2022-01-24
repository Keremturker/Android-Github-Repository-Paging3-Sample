package com.turker.github_repository_paging3_sample.network

import com.turker.github_repository_paging3_sample.data.repository.RepoRepository
import com.turker.github_repository_paging3_sample.data.repository.RepoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun provideRepoRepository(repoRepository: RepoRepositoryImpl): RepoRepository

    @Binds
    abstract fun bindAPIClientImpl(impl: APIClientImpl): APIClient

}