package com.example.mynasa

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitConnect {
    @GET("apod?&api_key=TwXcGEBDhnccH0HkvSC6N39sc1PEWVBhrsrn9hTc")
    fun getNews(@Query("date") date: String?): Call<ModelNews>

    @GET("photos?&api_key=TwXcGEBDhnccH0HkvSC6N39sc1PEWVBhrsrn9hTc")
    fun getMarsRoverImage(@Query("earth_date") date: String?): Call<ModelMars>

    @GET("{date}?api_key=TwXcGEBDhnccH0HkvSC6N39sc1PEWVBhrsrn9hTc")
    fun getDataEarthImage(@Path("date") date: String?): Call<List<ModelEarthImage>>
}