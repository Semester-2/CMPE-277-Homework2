package com.app.news

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.app.news.model.NewsData
import com.google.android.material.appbar.CollapsingToolbarLayout

const val SELECTED_NEWS_DATA = "Selected News Data"
class NewsDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val titleTV = findViewById<TextView>(R.id.titleTV)
        val authorTV =findViewById<TextView>(R.id.authorTV)
        val dateTimeTV = findViewById<TextView>(R.id.dateTimeTV)
        val descriptionTV = findViewById<TextView>(R.id.descriptionTV)
        val urlTV = findViewById<TextView>(R.id.urlTV)

        val toolbarLayout: CollapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.toolbarLayout)

        val dataItem: NewsData? = intent.extras?.get(SELECTED_NEWS_DATA) as? NewsData
        if (dataItem != null) {
            titleTV.text = dataItem.title
            authorTV.text = dataItem.author
            dateTimeTV.text = dataItem.publishedAt
            descriptionTV.text = dataItem.description
            urlTV.text = dataItem.url
            toolbarLayout.title = "Hello"
            toolbarLayout.setBackgroundResource(R.drawable.action)
        }
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}