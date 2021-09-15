package com.example.cleanarch.feature.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarch.R
import com.example.cleanarch.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private lateinit var bind: FragmentNewsBinding
    private val viewModel: NewsFragmentViewModel by viewModels()
    private val newsAdapter = NewsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bind = FragmentNewsBinding.inflate(layoutInflater)
        bind.lifecycleOwner = this
        bind.data = viewModel
        initView()
        return (bind.root)
    }

    private fun initView(){
        viewModel.newsData().observe(viewLifecycleOwner, {
            newsAdapter.submitList(it)
        })

        viewModel.errorMessage().observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        bind.rvNews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }

        newsAdapter.listener = {
            findNavController().navigate(R.id.action_newsFragment_to_detailFragment, Bundle().apply { putParcelable("data", it) })
        }
    }
}