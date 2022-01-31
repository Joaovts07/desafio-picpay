package com.picpay.desafio.android.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.domain.model.User
import kotlinx.android.synthetic.main.list_item_user.view.*

class UsersAdapter : ListAdapter<User,UsersAdapter.UsersViewHolder>(DIFF_CALLBACK) {

    class UsersViewHolder(
        private val itemUserBinding: ListItemUserBinding
    ): RecyclerView.ViewHolder(itemUserBinding.root){

        fun bind(user: User){
            itemUserBinding.run {
                Glide.with(itemView)
                    .load(user.img)
                    .fitCenter()
                    .into(picture)
                name.text = user.name
                username.text = user.username
            }
        }
        companion object{
            fun create(parent: ViewGroup): UsersViewHolder {
                val itemBinding = ListItemUserBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)

                return UsersViewHolder(itemBinding)
            }
        }

    }
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}