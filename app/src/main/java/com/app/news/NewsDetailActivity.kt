package com.app.news

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.util.Linkify
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.app.news.model.NewsData
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

const val SELECTED_NEWS_DATA = "Selected News Data"
class NewsDetailActivity : AppCompatActivity() , View.OnClickListener{
    lateinit var url: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val titleTV = findViewById<TextView>(R.id.titleTV)
        val authorTV = findViewById<TextView>(R.id.authorTV)
        val dateTimeTV = findViewById<TextView>(R.id.dateTimeTV)
        val descriptionTV = findViewById<TextView>(R.id.descriptionTV)
        val urlTV = findViewById<TextView>(R.id.urlTV)
        val collapsingImageView = findViewById<ImageView>(R.id.collapsingToolbarHeaderImage)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener(this)

        val toolbarLayout: CollapsingToolbarLayout =
            findViewById<CollapsingToolbarLayout>(R.id.toolbarLayout)

        val dataItem: NewsData? = intent.extras?.get(SELECTED_NEWS_DATA) as? NewsData
        if (dataItem != null) {
            titleTV.text = dataItem.title
            authorTV.text = dataItem.author
            dateTimeTV.text = dataItem.publishedAt
            descriptionTV.text = dataItem.description
            url = dataItem.url.toString()
            urlTV.text = url
            urlTV.setLinkTextColor(Color.parseColor("#00008B"))
            Linkify.addLinks(urlTV, Linkify.WEB_URLS)

            Glide
                .with(this)
                .load(dataItem.urlToImage)
                .centerCrop()
                .into(collapsingImageView);
            toolbarLayout.title = "News Detail"
        }



        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onClick(view: View?) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, url)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}
