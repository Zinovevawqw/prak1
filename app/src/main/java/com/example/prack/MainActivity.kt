package com.example.prack

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.emptyLongSet
import com.example.prack.databinding.ActivityMainBinding
import com.example.prack.databinding.ActivityPostCardBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter {
            viewModel.likeById(it.id)
        }
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
    }
}

val post = Post(
    id = 1,
    author = "БТПИТ",
    content = "Борисоглебский техникум промышленных и информационных технологий Общежитие Государственный Бюджетные места Лицензия/аккредитация ГБПОУ ВО «БТПИТ» образовано в соответствии с постановлением правительства Воронежской области от 20 мая 2015 года № 401 в результате реорганизации в форме слияния государственного образовательного бюджетного учреждения среднего профессионального образования Воронежской области «Борисоглебский индустриальный техникум», государственного образовательного бюджетного учреждения среднего профессионального образования Воронежской области «Борисоглебский техникум информатики и вычислительной техники» и государственного образовательного бюджетного учреждения начального профессионального образования Воронежской области «Профессиональное училище № 34 г. Борисоглебска», зарегистрировано в качестве юридического лица 11 сентября 2015 г.",
    published = "18.03 в 11:40",
    likeByMe = false,
    repost = 990, likes = 999
)



