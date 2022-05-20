package com.route.newsapp.Api

import com.route.newsapp.model.NewsResponse
import com.route.newsapp.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("v2/top-headlines/sources")
    fun getNewsSources(
            @Query("apiKey")apikey:String,
            @Query("category")category:String
    ):Call<SourcesResponse>
    @GET("v2/everything")
    fun getNews(
        @Query("apikey")apikey: String,
        @Query("sources")sources:String

    ):Call<NewsResponse>
}