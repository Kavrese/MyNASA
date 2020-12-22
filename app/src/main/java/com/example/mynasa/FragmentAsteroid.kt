package com.example.mynasa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_asteroid.*
import kotlinx.android.synthetic.main.item_asteroid.textView
import kotlinx.android.synthetic.main.item_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class FragmentAsteroid: Fragment() {
    val list_before: MutableList<Asteroid> = mutableListOf()
    val list_after: MutableList<Asteroid> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_asteroid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rec_asteroid_before.apply {
            adapter = TextItemAdapter(list_before)
            layoutManager = LinearLayoutManager(activity!!.applicationContext, LinearLayoutManager.HORIZONTAL, false)
        }

        rec_asteroid_after.apply {
            adapter = TextItemAdapter(list_after)
            layoutManager = LinearLayoutManager(activity!!.applicationContext, LinearLayoutManager.HORIZONTAL, false)
        }

        val now_calendar = Calendar.getInstance()
        now_calendar.time = Date()

        val after_calendar = Calendar.getInstance()
        after_calendar.time = Date()
        after_calendar.add(Calendar.DAY_OF_MONTH, 7)

        val before_calendar = Calendar.getInstance()
        before_calendar.time = Date()
        before_calendar.add(Calendar.DAY_OF_MONTH, - 7)

        val start_date_after = SimpleDateFormat("yyyy-MM-dd").format(now_calendar)
        val end_date_before = SimpleDateFormat("yyyy-MM-dd").format(now_calendar.add(Calendar.DAY_OF_MONTH, -1))
        val end_date_after = SimpleDateFormat("yyyy-MM-dd").format(after_calendar)
        val start_date_before = SimpleDateFormat("yyyy-MM-dd").format(before_calendar)

        getAsteroids(start_date_before, end_date_before, rec_asteroid_before, 0)
        getAsteroids(start_date_after, end_date_after, rec_asteroid_after, 1)
    }

    private fun getAsteroids(date_start: String, date_end: String, rec: RecyclerView, mode: Int){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/neo/rest/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(RetrofitConnect::class.java).getAsteroids(date_start, date_end).enqueue(object : Callback<AsteroidsDate>{
            override fun onFailure(call: Call<AsteroidsDate>, t: Throwable) {
                Toast.makeText(requireContext(),t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<AsteroidsDate>, response: Response<AsteroidsDate>) {
                if (response.body() != null) {
                    textView.visibility = View.GONE
                    rec_asteroid.visibility = View.VISIBLE
                    for (i in response.body()!!.near_earth_objects){
                        if (mode == 0) {
                            list_before.add(i)
                        }else{
                            if (mode == 1)
                                list_after.add(i)

                        }
                    }
                    rec.adapter?.notifyDataSetChanged()
                }else{
                    Toast.makeText(requireContext(), "Ответ Null", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}