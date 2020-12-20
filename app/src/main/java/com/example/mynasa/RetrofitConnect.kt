package com.example.mynasa

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitConnect {
    @GET("apod?date=2020-12-20&api_key=DEMO_KEY")
    fun getNews(): Call<ModelNews>
}