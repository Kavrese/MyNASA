package com.example.mynasa

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.item_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class FragmentNews: Fragment() {
    var list_ = mutableListOf<ModelNews>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rec_news.apply {
            adapter = NewsAdapterRec(list_)
            layoutManager = LinearLayoutManager(activity!!.applicationContext, LinearLayoutManager.HORIZONTAL, false)
        }

        val calendar = Calendar.getInstance()
        calendar.time = Date()
        for (i in 1..7) {
            val newdata: String = SimpleDateFormat("yyyy-MM-dd").format(calendar.time)
            getNews(newdata)
            calendar.add(Calendar.DAY_OF_MONTH, -1)
        }
    }
    fun getNews (date: String){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/planetary/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RetrofitConnect::class.java).getNews(date).enqueue(object: Callback<ModelNews>{
            override fun onFailure(call: Call<ModelNews>, t: Throwable) {
                Toast.makeText(requireContext(),t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ModelNews>, response: Response<ModelNews>) {
                if (response.body() != null) {
                    textView.visibility = View.GONE
                    rec_news.visibility = View.VISIBLE
                    list_.add(response.body()!!)
                    list_ = sortDate(list_)
                    rec_news.adapter!!.notifyDataSetChanged()
                }else{
                    Toast.makeText(requireContext(), "Ответ Null", Toast.LENGTH_LONG).show()
                }
            }

        })
    }
    private fun sortDate (list: MutableList<ModelNews>): MutableList<ModelNews>{
        if (list.size > 1) {
            for (i in 0..list.size - 1) {
                for (k in (i + 1)..list.size - 1) {
                    val sdf = SimpleDateFormat("yyyy-MM-dd")
                    val date_k = sdf.parse(list[k].date)
                    val date_i = sdf.parse(list[i].date)
                    if (date_i < date_k) {
                        val dop = list[i]
                        list[i] = list[k]
                        list[k] = dop
                        break
                    }
                }
            }
        }
        return list
    }
}