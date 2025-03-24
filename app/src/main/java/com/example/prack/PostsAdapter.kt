package com.example.prack

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.prack.databinding.ActivityPostCardBinding
import com.example.prack.PostViewHolder as PostViewHolder

typealias  OnLikeListener = (post : Post) ->Unit

class PostsAdapter (
    private val onLikeListener: OnLikeListener
): ListAdapter<Post,PostViewHolder>(PostDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ActivityPostCardBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return PostViewHolder(binding, onLikeListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}