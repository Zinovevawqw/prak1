package com.example.prack

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PostViewModel(application: Application) : AndroidViewModel(application){
    // упрощённый вариант
    private
    val repository: PostRepository = PostRepositorySharedPrefsImpl(application)
    val data = repository.getAll()


    private val empty = Post(
        id = 0,
        content = "",
        author = "",
        likeByMe = false,
        published = "",
        likes = 0,
        repost = 0,
        video = ""
    )
    val edited = MutableLiveData(empty)
    fun save(){
        edited.value?.let {
            repository.save(it)
        }
        edited.value= empty
    }

    fun  edit(post: Post){
        edited.value = post
    }

    fun changeContentAndSave(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value?.let {
            repository.save(it.copy(content = text))
        }
        edited.value = empty
    }
    fun likeById(id: Long) = repository.likeById(id)
    fun removeById(id:Long) = repository.removeById(id)
    fun repostById(id:Long) = repository.repostById(id)
}