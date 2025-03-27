package com.example.prack

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.prack.databinding.ActivityPostCardBinding
import com.example.prack.PostViewHolder as PostViewHolder

typealias  OnLikeListener = (post : Post) ->Unit
typealias  OnRemoveListener = (post : Post) ->Unit
typealias  OnRepostListener = (post : Post) ->Unit

class PostsAdapter(
    private val onLikeListener: OnLikeListener,
    private val onInteractionListener: OnInteractionListener,
    private val onRepostListener: OnRepostListener,

    ): ListAdapter<Post,PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        val binding = ActivityPostCardBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return PostViewHolder(binding, onLikeListener , onRepostListener,onInteractionListener )
    }

    override fun onBindViewHolder(
        holder: PostViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }
}