package com.example.prack

import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.prack.databinding.ActivityPostCardBinding

class PostViewHolder(
    private val binding: ActivityPostCardBinding,
    private val onLikeListener: OnLikeListener,
    private val onRemoveListener: OnRemoveListener,
    private val onInteractionListener: OnInteractionListener,


    ): RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            like.setImageResource(
                if (post.likeByMe) R.drawable.icons8_24__1_ else R.drawable.icons8_32
            )
            like.setOnClickListener {
                onLikeListener(post)
            }
        }
        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likes.text = formatNumber(post.likes)
            repost.text = formatNumber(post.repost)
            if (post.likeByMe) {
                like?.setImageResource(R.drawable.icons8_32)
            }
            like?.setOnClickListener {
                post.likeByMe = !post.likeByMe
                like.setImageResource(
                    if (post.likeByMe) R.drawable.icons8_24__1_ else R.drawable.icons8_32
                )
                if (post.likeByMe) {
                    post.likes = post.likes + 1
                    likes.text = formatNumber(post.likes)
                } else {
                    post.likes = post.likes - 1
                    likes.text = formatNumber(post.likes)
                }
            }

            imageButton2?.setOnClickListener {
                post.repost = post.repost + 1
                repost.text = formatNumber(post.repost)
            }

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.menu)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove ->{
                                onRemoveListener(post)
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