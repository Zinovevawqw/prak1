package com.example.prack

import androidx.lifecycle.viewmodel.viewModelFactory

interface OnInteractionListener {
    fun onEdit(post: Post){}
    fun onRemove(post: Post){}
    fun onLike(post: Post){}
    fun onRepost(post: Post)
}
