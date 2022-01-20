package com.turker.github_repository_paging3_sample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.turker.github_repository_paging3_sample.R
import com.turker.github_repository_paging3_sample.base.BaseActivity
import com.turker.github_repository_paging3_sample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override val viewModel: MainViewModel by viewModels()


    override fun onActivityCreated() {}

    override fun observe() {}
}