package com.example.cleanarch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding
    private lateinit var viewModel:MainActivityViewModel
    private val newsAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        bind.lifecycleOwner = this
        setContentView(bind.root)

        initView()
    }

    private fun initView(){
        viewModel = MainActivityViewModel()
        bind.data = viewModel

        viewModel.newsData().observe(this, {
            newsAdapter.submitList(it)
        })

        viewModel.errorMessage().observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        bind.rvNews.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsAdapter
        }
    }
}