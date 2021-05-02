package com.app.news.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.news.model.Category
import com.app.news.model.NewsData
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

        val list: List<NewsData> = ArrayList<NewsData>()
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


    /**
     * Call getWeatherProperties() on init so we can display status immediately.
     */


//    private fun getNewsData() {
//        //_response.value = "Set the Weather API Response here!"
//        //WeatherApi.retrofitService.getProperties()
//        NewsApi.retrofitService.getProperties("us", API_KEY).enqueue(
//            object: Callback<List<NewsData>> {
//                override fun onFailure(call: Call<List<NewsData>>, t: Throwable) {
//                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                    _response.value = "Failure: " + t.message
//                }
//
//                override fun onResponse(call: Call<List<NewsData>>, response: Response<List<NewsData>>) {
//                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                    //_response.value = response.body()
//                    _response.value = "Success: ${response.body()?.name} city retrieved; Temperature: ${response.body()?.mainpart?.temp}; Humidity: ${response.body()?.mainpart?.humidity}"
//                }
//
//            }
//        )
//    }
