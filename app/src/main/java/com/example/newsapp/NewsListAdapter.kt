package com.example.newsapp

import com.example.newsapp.NewsListAdapter.NewsItemClick
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.NewsListAdapter.NewsViewHolder
import com.example.newsapp.News
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.newsapp.R
import android.widget.TextView
import java.util.ArrayList

class NewsListAdapter(var listner: NewsItemClick) : RecyclerView.Adapter<NewsViewHolder>() {
    var items: ArrayList<News> = ArrayList<News>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        val holder = NewsViewHolder(view)
        view.setOnClickListener { listner.onItemClick(items[holder.absoluteAdapterPosition]) }
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        //        holder.textView.setText(currentItem);
        holder.textView.text = currentItem!!.title
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNews(updateNews: ArrayList<News>) {
        items.clear()
        items.addAll(updateNews)
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView

        init {
            textView = itemView.findViewById<View>(R.id.textView) as TextView
        }
    }

    interface NewsItemClick {
        fun onItemClick(value: News?)
    }
}