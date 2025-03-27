package com.example.prack

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken



class PostRepositorySharedPrefsImpl(
   private val context: Context,
): PostRepository {
    private val gson = Gson()
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
    private val filename = "posts.json"
    private var nextId = 3L
    private var posts = emptyList<Post>()
    private val data = MutableLiveData(posts)

    init {
        val file = context.filesDir.resolve(filename)
        if (file.exists()) {
            context.openFileInput(filename).bufferedReader().use {
                posts = gson.fromJson(it, type)
                data.value = posts
            }
        } else {
            sync()
        }
    }
        override fun save(post: Post) {
            if (post.id == 0L) {
                posts = listOf(
                    post.copy(
                        id = nextId++,
                        author = "Me",
                        likeByMe = false,
                        published = "now"
                    )
                ) + posts
                data.value = posts
                sync()
                return
            }
            posts = posts.map {
                if (it.id != post.id) it else it.copy(content = post.content)
            }
            data.value = posts
            sync()
        }

        override fun getAll(): LiveData<List<Post>> = data

        override fun likeById(id: Long) {
            posts = posts.map {
                if (it.id != id) it else it.copy(
                    likeByMe = !it.likeByMe,
                    likes = if (it.likeByMe) it.likes - 1 else it.likes + 1
                )
            }
            data.value = posts
            sync()
        }

        override fun removeById(id: Long) {
            posts = posts.filter { it.id != id }
            data.value = posts
            sync()
        }

        override fun repostById(id: Long) {
            posts = posts.map {
                if (it.id != id) it else it.copy(repost = it.repost + 1)
            }
            data.value = posts
            sync()
        }

        private fun sync() {
            context.openFileOutput(filename, Context.MODE_PRIVATE).bufferedWriter().use {
                it.write(gson.toJson(posts))
            }
        }
    }


//: PostRepository {
//    private var nextId = 3L
//    private var posts = listOf (
//        Post(
//            id = 1,
//            author = "БТПИТ",
//            content ="Борисоглебский техникум промышленных и информационных технологий Общежитие Государственный Бюджетные места Лицензия/аккредитация ГБПОУ ВО «БТПИТ» образовано в соответствии с постановлением правительства Воронежской области от 20 мая 2015 года № 401 в результате реорганизации в форме слияния государственного образовательного бюджетного учреждения среднего профессионального образования Воронежской области «Борисоглебский индустриальный техникум», государственного образовательного бюджетного учреждения среднего профессионального образования Воронежской области «Борисоглебский техникум информатики и вычислительной техники» и государственного образовательного бюджетного учреждения начального профессионального образования Воронежской области «Профессиональное училище № 34 г. Борисоглебска», зарегистрировано в качестве юридического лица 11 сентября 2015 г.",
//            published = "18.03 в 11:40",
//            likeByMe = false ,
//            repost = 990 , likes = 999,
//            video = "https://yandex.ru/video/preview/10521616147400397285"
//        ),
//        Post(
//            id = 2,
//            author = "БТПИТ",
//            content ="Борисоглебский техникум промышленных и информационных технологий Общежитие Государственный Бюджетные места Лицензия/аккредитация ГБПОУ ВО «БТПИТ» образовано в соответствии с постановлением правительства Воронежской области от 20 мая 2015 года № 401 в результате реорганизации в форме слияния государственного образовательного бюджетного учреждения среднего профессионального образования Воронежской области «Борисоглебский индустриальный техникум», государственного образовательного бюджетного учреждения среднего профессионального образования Воронежской области «Борисоглебский техникум информатики и вычислительной техники» и государственного образовательного бюджетного учреждения начального профессионального образования Воронежской области «Профессиональное училище № 34 г. Борисоглебска», зарегистрировано в качестве юридического лица 11 сентября 2015 г.",
//            published = "18.03 в 11:40",
//            likeByMe = false ,
//            repost = 990 , likes = 999,
//            video = "https://yandex.ru/video/preview/18148133030246839781"
//        ),
//    )