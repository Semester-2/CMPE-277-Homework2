package com.app.news.model

data class ResponseData(
        var status:String,
        var totalResults : String,
        var articles : List<NewsData>
)