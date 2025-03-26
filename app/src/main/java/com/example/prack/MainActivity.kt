package com.example.prack

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.prack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(
            onLikeListener = { post -> viewModel.likeById(post.id) },
            onRepostListener = { post -> viewModel.repostById(post.id) },
            onInteractionListener = object : OnInteractionListener {
                override fun onEdit(post: Post) {
                    binding.content.visibility = View.VISIBLE
                    viewModel.edit(post)
                }

                override fun onLike(post: Post) {
                    viewModel.likeById(post.id)
                }

                override fun onRepost(post: Post) {
                    viewModel.repostById(post.id)
                }

                override fun onRemove(post: Post) {
                    viewModel.removeById(post.id)
                }
            }
        )
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        binding.save.setOnClickListener{
            with(binding.content){
                if (text.isNullOrBlank()){
                    Toast.makeText(
                        this@MainActivity,
                        "Content can't be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                viewModel.changeContent(text.toString())
                viewModel.save()

                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }
        viewModel.edited.observe(this){post ->
            if (post.id ==0L){
                return@observe
            }
            with(binding.content){
                requestFocus()
                setText(post.content)
            }
        }
    }
}

object AndroidUtils {
    fun hideKeyboard(view: View){
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)
    }
}






