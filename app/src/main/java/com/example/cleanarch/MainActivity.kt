package com.example.cleanarch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarch.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModel()
    private val newsAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        bind.lifecycleOwner = this
        bind.data = viewModel
        setContentView(bind.root)
        initView()
    }

    private fun initView(){
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