package com.example.prack

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.prack.databinding.ActivityPostCardBinding
import com.example.prack.PostViewHolder as PostViewHolder

typealias  OnLikeListener = (post : Post) ->Unit
typealias  OnRemoveListener = (post : Post) ->Unit

class PostsAdapter(
    private val onLikeListener: OnLikeListener,
    private val onRemoveListener: OnRemoveListener,
    private val onInteractionListener: OnInteractionListener,

): ListAdapter<Post,PostViewHolder>(PostDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ActivityPostCardBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return PostViewHolder(binding, onLikeListener,onRemoveListener,onInteractionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}