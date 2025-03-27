package com.example.prack


data class Post (
    val id: Long,
    val author : String,
    val content : String,
    val published :String,
    var likeByMe: Boolean,
    var repost: Int,
    var likes: Int,

    val video: String?
)