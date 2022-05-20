package com.route.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.route.newsapp.model.ArticlesItem

class NewsAdapter(var items: List<ArticlesItem?>? = null) :RecyclerView.Adapter<NewsAdapter.viewHolder>() {

    class viewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        val title:TextView=itemView.findViewById(R.id.title)
        val author:TextView=itemView.findViewById(R.id.author)
        val image:ImageView=itemView.findViewById(R.id.image)
        val datetime:TextView=itemView.findViewById(R.id.dateTime)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item=items?.get(position)
        holder.title.setText(item?.title)
        holder.author.setText(item?.author)
        holder.datetime.setText(item?.publishedAt)
        Glide.with(holder.itemView)
            .load(item?.urlToImage)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
return  items?.size ?:0
    }

   public fun changeData(data: List<ArticlesItem?>?) {
items= data
        notifyDataSetChanged()
    }
}