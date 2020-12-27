package com.example.mynasa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.bottom_tinder.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BottomTinder: Fragment() {
    var list = mutableListOf<ModelTinder>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_tinder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getListModelTinder()
        rec_tinder.apply {
            layoutManager =  LinearLayoutManager(activity!!.applicationContext)
            adapter = AdapterTinder(list)
        }
    }

    private fun getListModelTinder(){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://images-api.nasa.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RetrofitConnect::class.java).getSearch().enqueue(object : Callback<ModelCollection>{
            override fun onFailure(call: Call<ModelCollection>, t: Throwable) {
                Toast.makeText(requireContext(),t.message, Toast.LENGTH_LONG).show()
            }
            override fun onResponse(
                call: Call<ModelCollection>,
                response: Response<ModelCollection>
            ) {
                if (response.body() != null) {
                    val base = response.body()!!.collection!!.items!!
                    for (i in base){
                        list.add(ModelTinder(i.data!![0].title.toString(), i.links!![0].href.toString(), i.data!![0].description.toString()))
                        rec_tinder.adapter?.notifyDataSetChanged()
                    }
                }else{
                    Toast.makeText(requireContext(), "Ответ Null", Toast.LENGTH_LONG).show()
                }
            }

        })
    }
}