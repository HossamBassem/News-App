package com.route.newsapp.ui.news

import android.util.Log

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.newsapp.Api.ApiManager
import com.route.newsapp.Constants
import com.route.newsapp.model.ArticlesItem
import com.route.newsapp.model.NewsResponse
import com.route.newsapp.model.SourcesItem
import com.route.newsapp.model.SourcesResponse
import com.route.newsapp.ui.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel:ViewModel() {
    val sourcesLiveData =MutableLiveData<List<SourcesItem?>?>()
    val progressBarVisible=MutableLiveData<Boolean>(false)
    val newsList=MutableLiveData<List<ArticlesItem?>?>()
    val messageLiveData=MutableLiveData<String>()
     fun getNewsSources(category: Category) {
         progressBarVisible.value=true
        ApiManager.getApis()
            .getNewsSources(Constants.apikey, category.id)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {

                    progressBarVisible.value = false;
                    messageLiveData.value=t.localizedMessage
                }

                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                   progressBarVisible.value = false;
                    sourcesLiveData.value=response.body()?.sources
                    Log.e("response", response.body().toString())
                }
            })
    }

    fun loadNews(source: SourcesItem) {
//        adapter.changeData(null)
        progressBarVisible.value = true;
        ApiManager.getApis()
            .getNews(Constants.apikey, source.id ?: "")
            .enqueue(object : retrofit2.Callback<NewsResponse> {
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    messageLiveData.value=t.localizedMessage
//
                    progressBarVisible.value = false;

                }

                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    progressBarVisible.value = false;

                    newsList.value=response.body()?.articles
                }
            })

    }
}