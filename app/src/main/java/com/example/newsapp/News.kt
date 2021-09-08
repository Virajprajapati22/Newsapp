package com.example.newsapp

import androidx.annotation.RequiresApi
import android.os.Build
import com.example.newsapp.News
import java.util.*

data class News(
    var title: String,
    var author: String,
    var url: String,
    var urltoimage: String
)