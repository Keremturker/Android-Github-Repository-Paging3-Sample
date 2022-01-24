package com.turker.github_repository_paging3_sample.network

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.turker.github_repository_paging3_sample.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


const val CONNECTION_TIMEOUT_SEC = 5 * 60L

interface APIClient {
    val apiCollect: RepoService
}

@Singleton
class APIClientImpl  @Inject constructor(@ApplicationContext context: Context) : APIClient {

    override val apiCollect: RepoService by lazy {
        clientCollect.create(RepoService::class.java)
    }

    private val clientCollect: Retrofit by lazy {
        retrofitBuilderCollect.client(okHttpClientCollect).build()
    }

    private val stethoInterceptor: StethoInterceptor? by lazy {
        if (BuildConfig.DEBUG) StethoInterceptor() else null
    }

    private val retrofitBuilderCollect: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    }

    private val okHttpClientCollect: OkHttpClient by lazy {
        okHttpClientBuilderCollect.addInterceptor { chain ->
            val builder = chain.request().newBuilder()
            chain.proceed(builder.build())
        }
        okHttpClientBuilderCollect.build()
    }

    private val okHttpClientBuilderCollect by lazy {

        val builder = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
            .readTimeout(CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
            .addInterceptor(NetworkConnectionInterceptor(context))
        stethoInterceptor?.let { builder.addNetworkInterceptor(it) }

        builder
    }
}