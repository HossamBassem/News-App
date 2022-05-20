package com.route.newsapp.Api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiManager {
    companion object{
      private  var retrofit:Retrofit?=null
      private  fun getInstance(): Retrofit {
          val logging = HttpLoggingInterceptor()
          logging.setLevel(HttpLoggingInterceptor.Level.BODY)
          val client = OkHttpClient.Builder()
                  .addInterceptor(logging)
                  .build()
if (retrofit==null){
    retrofit=Retrofit.Builder()
            .client(client)
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}
return retrofit!!;

        }
fun getApis():WebServices{
    return getInstance().create(WebServices::class.java)

}


    }
}