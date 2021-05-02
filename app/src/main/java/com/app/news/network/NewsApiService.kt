package com.app.news.network

import com.app.news.model.NewsData
import com.app.news.model.ResponseData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://newsapi.org"

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface NewsApiService {

    @GET("/v2/top-headlines/")
    fun getProperties(@Query("country") country: String, @Query("apiKey") apiKey: String):
            Call<ResponseData>

    @GET("/v2/top-headlines/")
    fun getCategories(@Query("category") category: String, @Query("apiKey") apiKey: String):
            Call<ResponseData>

    @GET("/v2/top-headlines/")
    fun getSearchResult(@Query("q") searchItem: String, @Query("apiKey") apiKey: String):
            Call<ResponseData>

}

object NewsApi {
    val retrofitService : NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java) }
}


