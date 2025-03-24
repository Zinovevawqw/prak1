package com.example.prack

import androidx.lifecycle.ViewModel

class PostViewModel :ViewModel() {
    private  val repository: PostRepository = PostRepositoryImMemoryImpl()
    val data = repository.getAll()
    fun likeById(id: Long) = repository.likeById(id)
}