package com.example.mynasa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.item_earth.*
import kotlinx.android.synthetic.main.item_mars.*
import kotlinx.android.synthetic.main.item_mars.textView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragmentEarth: Fragment() {
    private val list: MutableList<ModelImage> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_earth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rec_earth.apply {
            adapter = AdapterImage(list)
            layoutManager = LinearLayoutManager(requireContext())
        }
        getImageData()
    }
    private fun getImageData(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/EPIC/api/natural/date/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val years ="2019"
        val month = "05"
        val day = "30"
            retrofit.create(RetrofitConnect::class.java).getDataEarthImage("$years-$month-$day").enqueue(object :
            Callback<List<ModelEarthImage>> {
            override fun onFailure(call: Call<List<ModelEarthImage>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<ModelEarthImage>>, response: Response<List<ModelEarthImage>>) {
                if (response.body() != null){
                    textView.visibility = View.GONE
                    rec_earth.visibility = View.VISIBLE
                    val li = response.body()
                    for (i in li!!){
                        val code_img = i. image
                        list.add(ModelImage("https://api.nasa.gov/EPIC/archive/natural/$years/$month/$day/png/$code_img.png?api_key=TwXcGEBDhnccH0HkvSC6N39sc1PEWVBhrsrn9hTc", i.date))
                    }
                    rec_earth.adapter?.notifyDataSetChanged()
                }else{
                    Toast.makeText(requireContext(), "Null", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}