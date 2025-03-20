package com.example.prack

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.emptyLongSet
import com.example.prack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "БТПИТ",
            content ="Борисоглебский техникум промышленных и информационных технологий Общежитие Государственный Бюджетные места Лицензия/аккредитация ГБПОУ ВО «БТПИТ» образовано в соответствии с постановлением правительства Воронежской области от 20 мая 2015 года № 401 в результате реорганизации в форме слияния государственного образовательного бюджетного учреждения среднего профессионального образования Воронежской области «Борисоглебский индустриальный техникум», государственного образовательного бюджетного учреждения среднего профессионального образования Воронежской области «Борисоглебский техникум информатики и вычислительной техники» и государственного образовательного бюджетного учреждения начального профессионального образования Воронежской области «Профессиональное училище № 34 г. Борисоглебска», зарегистрировано в качестве юридического лица 11 сентября 2015 г.",
            published = "18.03 в 11:40",
            likeByMe = false,
            repost = 990 , likes = 999
        )
        with (binding){
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likes.text = formatNumber(post.likes)
            repost.text = formatNumber(post.repost)
            if (post.likeByMe){
                like?.setImageResource(R.drawable.icons8_32)
            }
            like?.setOnClickListener{
                post.likeByMe =!post.likeByMe
                like.setImageResource(
                    if  (post.likeByMe) R.drawable.icons8_24__1_ else R.drawable.icons8_32
                )
                if(post.likeByMe){
                    post.likes = post.likes+1
                    likes.text = formatNumber(post.likes)
                }
                else{
                    post.likes = post.likes-1
                    likes.text = formatNumber(post.likes)
                }
            }

            imageButton2?.setOnClickListener{
                post.repost = post.repost+1
                repost.text = formatNumber(post.repost)
            }
        }
    }private fun formatNumber(number: Int): String{
        return when{
            number >= 1_000_000 -> String.format("%.1fM",number / 1_000_000.0).replace(",",".")
            number >= 1_000 -> String.format("%.1fK",number / 1_000.0)
            else -> number.toString()
        }
    }
}