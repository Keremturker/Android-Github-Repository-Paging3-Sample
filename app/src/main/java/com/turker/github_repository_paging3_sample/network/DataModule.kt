package com.turker.github_repository_paging3_sample.network

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindAPIClientImpl(impl: APIClientImpl): APIClient

}