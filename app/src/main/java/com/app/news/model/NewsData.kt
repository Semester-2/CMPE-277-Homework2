package com.app.news.model

import java.io.Serializable

data class NewsData(var source: Source, var author : String, var title: String, var description: String, var url: String, var urlToImage: String, var publishedAt: String,
                        var content:String) : Serializable
