package com.example.cleanarch.feature.news

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

    var listener:((NewsData)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
        holder.binding.cardNewsItem.setOnClickListener {
            listener?.invoke(currentList[position])
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class ViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NewsData) {
            binding.itemData = data
            binding.executePendingBindings()
        }

        companion object {
            @JvmStatic
            @BindingAdapter("imageUrl")
            fun setImage(view: ImageView, url: String) {
                Glide.with(view.context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .thumbnail(0.5f)
                    .centerCrop()
                    .into(view)
            }
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
