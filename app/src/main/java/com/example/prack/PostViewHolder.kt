package com.example.prack

import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.prack.databinding.ActivityPostCardBinding

class PostViewHolder(
    private val binding: ActivityPostCardBinding,
    private val onLikelistener: OnLikeListener,
    private val onRepostListener: OnRepostListener,
    private val onInteractionListener: OnInteractionListener,

    ) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            repost.text = formatNumber(post.repost)
            content.text = post.content
            likes.text =  formatNumber(post.likes)
            like.isChecked = post.likeByMe
            like.setOnClickListener {
                if (post.likeByMe) {
                    likes.text = formatNumber(post.likes)
                } else {
                    likes.text = formatNumber(post.likes)
                }
                onLikelistener(post)
            }
            imageButton2.setOnClickListener {
                repost.text = formatNumber(post.repost)
                onRepostListener(post)
            }

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.menu)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove ->{
                                onInteractionListener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
        }
    }

    private fun formatNumber(number: Int): String {
        return when {
            number >= 1_000_000 -> String.format("%.1fM", number / 1_000_000.0)
                .replace(",", ".")

            number >= 1_000 -> String.format("%.1fK", number / 1_000.0)
            else -> number.toString()
        }
    }
}