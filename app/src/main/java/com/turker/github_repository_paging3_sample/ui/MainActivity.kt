package com.turker.github_repository_paging3_sample.ui

import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.turker.github_repository_paging3_sample.R
import com.turker.github_repository_paging3_sample.base.BaseActivity
import com.turker.github_repository_paging3_sample.data.model.RepoModel
import com.turker.github_repository_paging3_sample.databinding.ActivityMainBinding
import com.turker.github_repository_paging3_sample.ui.adapter.ReposAdapter
import com.turker.github_repository_paging3_sample.ui.common.FooterAdapter
import com.turker.github_repository_paging3_sample.utils.collect
import com.turker.github_repository_paging3_sample.utils.collectLast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var repoAdapter: ReposAdapter


    override fun onActivityCreated() {
        binding.apply {
            searchRepo.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    callApi()
                    true
                } else {
                    false
                }
            }
            searchRepo.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    callApi()
                    true
                } else {
                    false
                }
            }

            btnRetry.setOnClickListener {
                callApi()
            }
        }


        setAdapter()
    }

    override fun observe() {}

    private fun setAdapter() {
        collect(flow = repoAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setReposUiState
        )
        binding.rvRepos.adapter = repoAdapter.withLoadStateFooter(FooterAdapter(repoAdapter::retry))
    }

    private fun setReposUiState(loadState: LoadState) {
        when (loadState) {
            is LoadState.Loading -> {
                binding.rvRepos.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
                binding.btnRetry.visibility = View.GONE
                binding.tvError.visibility = View.GONE
            }

            is LoadState.NotLoading -> {
                binding.rvRepos.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.btnRetry.visibility = View.GONE
                binding.tvError.visibility = View.GONE
            }

            is LoadState.Error -> {
                binding.rvRepos.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
                binding.btnRetry.visibility = View.VISIBLE
                binding.tvError.visibility = View.VISIBLE
                binding.tvError.text = loadState.error.localizedMessage ?: getString(R.string.something_went_wrong)
            }
        }
    }

    private suspend fun setRepos(reposItemsPagingData: PagingData<RepoModel>) {
        repoAdapter.submitData(reposItemsPagingData)
    }

    private fun callApi() {
        val query = binding.searchRepo.text.toString().trim()
        if (query.isNotEmpty()) {
            collectLast(viewModel.callRepo(query), ::setRepos)
        }
    }
}