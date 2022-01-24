package com.turker.github_repository_paging3_sample.ui.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.turker.github_repository_paging3_sample.R
import com.turker.github_repository_paging3_sample.data.model.RepoModel
import com.turker.github_repository_paging3_sample.databinding.ItemRepoListBinding


class RepoViewHolder(private val binding: ItemRepoListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(item: RepoModel) {

        binding.apply {
            repoName.text = item.fullName
            repoDescription.text = item.description
            repoStars.text = item.stars.toString()
            repoForks.text = item.forks.toString()
            repoLanguage.text =
                binding.repoLanguage.context.getString(R.string.language, item.language)
        }

    }
}