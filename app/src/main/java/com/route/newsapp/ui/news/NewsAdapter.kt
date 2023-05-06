package com.route.newsapp.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.route.newsapp.R
import com.route.newsapp.databinding.ItemNewsBinding
import com.route.newsapp.model.ArticlesItem

class NewsAdapter(var items: List<ArticlesItem?>? = null) :
    RecyclerView.Adapter<NewsAdapter.viewHolder>() {

    class viewHolder(val itemBinding: ItemNewsBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: ArticlesItem?) {
            itemBinding.item = item
            itemBinding.invalidateAll()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

        val viewBinding: ItemNewsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_news,
            parent,
            false
        )
        return viewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = items?.get(position)
         holder.bind(item)

    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    public fun changeData(data: List<ArticlesItem?>?) {
        items = data
        notifyDataSetChanged()
    }
}