package com.example.mynasa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragmentNews: Fragment() {
    val list_ = mutableListOf<ModelNews>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getNews("")
    }
    fun getNews (date: String){
        val list = mutableListOf<ModelNews>()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/planetary/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RetrofitConnect::class.java).getNews().enqueue(object: Callback<ModelNews>{
            override fun onFailure(call: Call<ModelNews>, t: Throwable) {

            }

            override fun onResponse(call: Call<ModelNews>, response: Response<ModelNews>) {
               list_.add(response.body()!!)

            }

        })
    }
}