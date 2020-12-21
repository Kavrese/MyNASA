package com.example.mynasa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.item_mars.*
import kotlinx.android.synthetic.main.item_mars.textView
import kotlinx.android.synthetic.main.item_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragmentMars: Fragment() {
    private val list: MutableList<Photos> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_mars, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rec_mars.apply {
            adapter = AdapterImage(list)
            layoutManager = LinearLayoutManager(requireContext())
        }
        getImage()
    }

    private fun getImage(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RetrofitConnect::class.java).getMarsRoverImage("2020-12-19").enqueue(object : Callback<ModelMars>{
            override fun onFailure(call: Call<ModelMars>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ModelMars>, response: Response<ModelMars>) {
                if (response.body() != null){
                    textView.visibility = View.GONE
                    rec_mars.visibility = View.VISIBLE
                    for (i in response.body()!!.photos!!){
                        list.add(i)
                    }
                    rec_mars.adapter?.notifyDataSetChanged()
                }else{
                    Toast.makeText(requireContext(), "Null", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}