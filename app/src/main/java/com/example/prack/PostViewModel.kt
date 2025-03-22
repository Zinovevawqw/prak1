package com.example.prack

import androidx.lifecycle.ViewModel

class PostViewModel :ViewModel() {
    private  val repository: PostRepository = PostRepositoryImMemoryImpl()
    val data = repository.get()
    fun like() = repository.like()
}