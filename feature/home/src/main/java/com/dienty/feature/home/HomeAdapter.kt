package com.dienty.feature.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dienty.common.model.entity.UserEntity
import com.dienty.feature.home.databinding.ItemUserBinding

class HomeAdapter(private val callback: Listener): PagingDataAdapter<UserEntity, HomeAdapter.ViewHolder>(MovieComparator){

    interface Listener {
        fun onClickedItem(username: String)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item  = getItem(position) ?: return
        holder.binding.apply {
            username = item.username
            htmlLink = item.htmlUrl
            Glide.with(root.context).load(item.avatarUrl).into(imgAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false
        )
        return ViewHolder(binding as ItemUserBinding)
    }

    inner class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                getItem(bindingAdapterPosition)?.username?.run {
                    callback.onClickedItem(this)
                }
            }
        }
    }

    object MovieComparator : DiffUtil.ItemCallback<UserEntity>() {
        override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem.username == newItem.username
        }
        override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem == newItem
        }
    }
}