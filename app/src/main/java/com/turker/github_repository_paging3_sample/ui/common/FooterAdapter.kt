package com.turker.github_repository_paging3_sample.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.turker.github_repository_paging3_sample.databinding.ItemPagingFooterBinding


class FooterAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<FooterViewHolder>() {
    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): FooterViewHolder {

        val itemPagingFooterBinding =
            ItemPagingFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FooterViewHolder(itemPagingFooterBinding, retry)
    }

}

