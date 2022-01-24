package com.turker.github_repository_paging3_sample.ui.common

import android.view.View
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.turker.github_repository_paging3_sample.R
import com.turker.github_repository_paging3_sample.databinding.ItemPagingFooterBinding


class FooterViewHolder(
    private val binding: ItemPagingFooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnRetry.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {

        when (loadState) {

            is LoadState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.tvError.visibility = View.GONE
                binding.btnRetry.visibility = View.GONE

            }
            is LoadState.Error -> {
                binding.progressBar.visibility = View.GONE
                binding.tvError.visibility = View.VISIBLE
                binding.btnRetry.visibility = View.VISIBLE
                binding.tvError.text = loadState.error.localizedMessage
            }
            is LoadState.NotLoading -> {}
        }

    }
}

