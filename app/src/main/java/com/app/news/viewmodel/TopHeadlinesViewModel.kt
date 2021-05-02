package com.app.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.news.model.Category
import com.app.news.model.ResponseData
import com.app.news.network.NewsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val API_KEY = "0e80bc8a464f4a99b68dd7fe1ee928b3"
class TopHeadlinesViewModel : ViewModel() {

    var headlinesLiveData: MutableLiveData<ResponseData> = MutableLiveData()
    var response : MutableLiveData<String> = MutableLiveData()


    fun fetchHeadlines() {

        NewsApi.retrofitService.getProperties("us", API_KEY).enqueue(
            object : Callback<ResponseData> {
                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    response.value = "News Fetch Failed: "+ t.message
                }

                override fun onResponse(
                    call: Call<ResponseData>,
                    response: Response<ResponseData>
                ) {
                    headlinesLiveData.postValue(response.body())
                }
            }
        )
    }

    fun fetchNewsForCategory(category: Category) {
        category.name
        NewsApi.retrofitService.getCategories(category.name, API_KEY).enqueue(
            object : Callback<ResponseData> {
                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    response.value = "News Fetch Failed: "+ t.message
                }

                override fun onResponse(
                    call: Call<ResponseData>,
                    response: Response<ResponseData>
                ) {
                    headlinesLiveData.postValue(response.body())
                }
            }
        )
    }
}



