package com.example.prack

import android.net.ConnectivityDiagnosticsManager.DataStallReport

data class Post (
    val id: Long,
    val author : String,
    val content : String,
    val published :String,
    var likeByMe: Boolean = false,
    var repost: Int,
    var likes: Int
)