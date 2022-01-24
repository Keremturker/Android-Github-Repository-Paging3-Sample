package com.turker.github_repository_paging3_sample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.turker.github_repository_paging3_sample.data.model.RepoModel
import com.turker.github_repository_paging3_sample.databinding.ItemRepoListBinding
import javax.inject.Inject


class ReposAdapter @Inject constructor() :
    PagingDataAdapter<RepoModel, RepoViewHolder>(Comparator) {

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        getItem(position)?.let { repoModel -> holder.bind(repoModel) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {

        val binding = ItemRepoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return RepoViewHolder(binding)
    }

    object Comparator : DiffUtil.ItemCallback<RepoModel>() {
        override fun areItemsTheSame(oldItem: RepoModel, newItem: RepoModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RepoModel,
            newItem: RepoModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}