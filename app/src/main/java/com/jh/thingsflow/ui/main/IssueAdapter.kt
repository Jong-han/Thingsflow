package com.jh.thingsflow.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jh.thingsflow.base.BaseModel
import com.jh.thingsflow.base.BaseViewHolder
import com.jh.thingsflow.databinding.ActivityMainBannerItemBinding
import com.jh.thingsflow.databinding.ActivityMainIssueItemBinding

class IssueAdapter: ListAdapter<BaseModel, BaseViewHolder>(IssueDiffUtil()) {

    companion object {
        private const val ISSUE = 0
        private const val BANNER = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is BaseModel.ResultIssueItem -> ISSUE
            is BaseModel.BannerModel -> BANNER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ISSUE -> {
                val binding = ActivityMainIssueItemBinding.inflate(layoutInflater, parent, false)
                IssueViewHolder(binding)
            }
            else -> {
                val binding = ActivityMainBannerItemBinding.inflate(layoutInflater, parent, false)
                BannerViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}

class BannerViewHolder(private val binding: ActivityMainBannerItemBinding): BaseViewHolder(binding) {
    override fun onBind(item: BaseModel) {
        if (item is BaseModel.BannerModel) {
            binding.data = item
        }
    }
}

class IssueViewHolder(private val binding: ActivityMainIssueItemBinding): BaseViewHolder(binding) {
    override fun onBind(item: BaseModel) {
        if (item is BaseModel.ResultIssueItem) {
            binding.data = item
            Log.i("asdf","issue number :: ${item.number}")
        }
    }
}

class IssueDiffUtil: DiffUtil.ItemCallback<BaseModel>() {
    override fun areItemsTheSame(oldItem: BaseModel, newItem: BaseModel): Boolean {
        return if (oldItem is BaseModel.ResultIssueItem && newItem is BaseModel.ResultIssueItem)
            oldItem.id == newItem.id
        else true
    }

    override fun areContentsTheSame(oldItem: BaseModel, newItem: BaseModel): Boolean {
        return oldItem == newItem
    }

}