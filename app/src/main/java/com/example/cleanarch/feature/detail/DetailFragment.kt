package com.example.cleanarch.feature.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cleanarch.R
import com.example.cleanarch.databinding.FragmentDetailBinding
import com.example.cleanarch.model.NewsData

class DetailFragment : Fragment() {

    private lateinit var bind: FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bind = FragmentDetailBinding.inflate(layoutInflater)
        initView()
        return bind.root
    }

    private fun initView() {
        val args: NewsData? = arguments?.getParcelable("data")
        bind.lifecycleOwner = this
        bind.data = args
    }
}

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String?){
    Glide.with(view.context)
        .load(url)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
}