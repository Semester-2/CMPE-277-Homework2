package com.app.news.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.app.news.NewsDetailActivity
import com.app.news.R
import com.app.news.SELECTED_NEWS_DATA
import com.app.news.model.NewsData
import java.io.Serializable

class NewsDataListAdapter () :
    RecyclerView.Adapter<NewsDataListAdapter.CardHolder>(){

    private var newsList:List<NewsData> = ArrayList<NewsData>()

    class CardHolder(private val view: View): RecyclerView.ViewHolder(view){
        val newsText : TextView = view.findViewById(R.id.headingTV)
        private val newsImage : ImageView = view.findViewById(R.id.headingIV)
        private val card : CardView = view.findViewById(R.id.card)

        fun bind(oneItem: NewsData) {
            newsText.text = oneItem.title
            //newsImage.text = oneItem.rating

            val context = view.context
            card.setOnClickListener {

                val intent = Intent(context, NewsDetailActivity::class.java).apply {
                    putExtra(SELECTED_NEWS_DATA, oneItem as Serializable)
                }
                context.startActivity(intent)
            }
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.news_data_item_view, viewGroup, false)

        return CardHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(newsList[position])
    }

    fun updateList(newsList : List<NewsData>){
        this.newsList = newsList
    }
}