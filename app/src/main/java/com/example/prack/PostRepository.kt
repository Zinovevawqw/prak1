package com.example.prack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
}

class PostRepositoryImMemoryImpl : PostRepository {
    private var post = Post(
        id = 1,
        author = "БТПИТ",
        content ="Борисоглебский техникум промышленных и информационных технологий Общежитие Государственный Бюджетные места Лицензия/аккредитация ГБПОУ ВО «БТПИТ» образовано в соответствии с постановлением правительства Воронежской области от 20 мая 2015 года № 401 в результате реорганизации в форме слияния государственного образовательного бюджетного учреждения среднего профессионального образования Воронежской области «Борисоглебский индустриальный техникум», государственного образовательного бюджетного учреждения среднего профессионального образования Воронежской области «Борисоглебский техникум информатики и вычислительной техники» и государственного образовательного бюджетного учреждения начального профессионального образования Воронежской области «Профессиональное училище № 34 г. Борисоглебска», зарегистрировано в качестве юридического лица 11 сентября 2015 г.",
        published = "18.03 в 11:40",
        likeByMe = false ,
        repost = 990 , likes = 999
    )
    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data
    override fun like(){
        post = post.copy(likeByMe = !post.likeByMe)
        data.value= post
    }
}