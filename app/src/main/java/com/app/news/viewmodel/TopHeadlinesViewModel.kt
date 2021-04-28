package com.app.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.news.model.Category
import com.app.news.model.NewsData

class TopHeadlinesViewModel : ViewModel() {

    var headlinesLiveData : MutableLiveData<List<NewsData>> = MutableLiveData()
    var URL : String = "https://newsapi.org/v2/top-headlines?country=us&apiKey=0e80bc8a464f4a99b68dd7fe1ee928b3"

   fun fetchHeadlines(){

       val list: List<NewsData> = ArrayList<NewsData>()

       headlinesLiveData.postValue(list)

   }

    fun fetchNewsForCategory(category : Category){
        category.name
    }
}