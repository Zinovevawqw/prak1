package com.example.prack

import androidx.lifecycle.viewmodel.viewModelFactory

interface OnInteractionListener {
    fun OnLike(post: Post){}
    fun onEdit(post: Post){}
    fun onRemove(post: Post){}
    fun onLike(post: Post)
}
