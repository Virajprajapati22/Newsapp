package com.example.newsapp

import android.os.Bundle
import android.service.autofill.VisibilitySetterAction
import android.text.Layout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.newsapp.NewsListAdapter.NewsItemClick
import com.example.newsapp.R.id
import com.example.newsapp.R.layout
import org.json.JSONException
import java.util.*

class MainActivity : AppCompatActivity(), NewsItemClick {
    private lateinit var newsArray: ArrayList<News>
    private lateinit var mAdapter: NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val progressBar = findViewById<ProgressBar>(id.progressBar)
        fetchData()
        //        ArrayList<String> items = fetchData();
        mAdapter = NewsListAdapter(this)
        recyclerView.adapter = mAdapter
    }

    private fun fetchData() {
        val queue = Volley.newRequestQueue(this)
        val url =
            "https://newsapi.org/v2/everything?q=tesla&from=2021-07-12&sortBy=publishedAt&apiKey=5759dc319cd34bba9bf5089724b09ed9"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            try {
                val newsJsonArray = response.getJSONArray("articles")
                newsArray = ArrayList()
                for (i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    newsArray.add(
                        News(
                            newsJsonObject.getString("title"),
                            newsJsonObject.getString("author"),
                            newsJsonObject.getString("url"),
                            newsJsonObject.getString("urltoimage")
                        )
                    )
                }
                mAdapter.updateNews(newsArray)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }) {}
        queue.add(jsonObjectRequest)
//        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClick(value: News?) {}
}