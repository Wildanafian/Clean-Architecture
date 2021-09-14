package com.example.cleanarch

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cleanarch.databinding.NewsItemBinding
import com.example.cleanarch.model.NewsData

class NewsAdapter : ListAdapter<NewsData, NewsAdapter.ViewHolder>(NewsAdapterDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class ViewHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NewsData) {
            binding.itemData = data
            binding.executePendingBindings()
        }
    }
}

class NewsAdapterDiffUtils : DiffUtil.ItemCallback<NewsData>() {
    override fun areItemsTheSame(oldItem: NewsData, newItem: NewsData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: NewsData, newItem: NewsData): Boolean {
        return oldItem.publishedAt == newItem.publishedAt
    }
}

@BindingAdapter("imageUrl")
fun setImage(view: ImageView, url: String) {
    url.let {
        Glide.with(view.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .override(75, 75)
            .centerCrop()
            .into(view)
    }
}
