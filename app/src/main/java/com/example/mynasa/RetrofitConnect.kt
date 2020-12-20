package com.example.mynasa

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitConnect {
    @GET("apod?&api_key=DEMO_KEY")
    fun getNews(@Query("date") date: String?): Call<ModelNews>
}