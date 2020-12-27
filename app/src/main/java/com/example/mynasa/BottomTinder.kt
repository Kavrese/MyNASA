package com.example.mynasa

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.bottom_tinder.*
import kotlinx.android.synthetic.main.bottom_tinder.img
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BottomTinder: Fragment() {
    var list = mutableListOf<ModelTinder>()
    private var page = 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_tinder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getListModelTinder()
        motion_tinder.setTransitionListener(object : MotionLayout.TransitionListener{
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                if (p2 == R.id.like){
                    dop_img.setImageResource(R.drawable.good)
                }else{
                    if (p2 == R.id.dislike){
                        dop_img.setImageResource(R.drawable.bad)
                    }
                }
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {

            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                if (p1 != R.id.start) {
                    minus()
                    p0?.progress = 0.0f
                }
            }
        })
        good.setOnClickListener {
            clickToRect(R.id.toLike)
            dop_img.setImageResource(R.drawable.good)
        }
        bad.setOnClickListener {
            clickToRect(R.id.toDislike)
            dop_img.setImageResource(R.drawable.bad)
        }
    }

    private fun clickToRect(tr: Int){
        motion_tinder.setTransition(tr)
        motion_tinder.transitionToEnd()
        Handler().postDelayed({minus()},600)
    }

    private fun minus(){
        if (list.size == 0) {
            page++
            getListModelTinder()
        }else{
            list.removeAt(0)
            setModel(list[0])
        }
    }

    private fun getListModelTinder(){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://images-api.nasa.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RetrofitConnect::class.java).getSearch(page.toString()).enqueue(object : Callback<ModelCollection>{
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
                    }
                    setModel(list[0])
                }else{
                    Toast.makeText(requireContext(), "Ответ Null", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun setModel(model: ModelTinder){
        Picasso.get()
            .load(model.img)
            .into(img)
        name.text = model.name
        text.text = model.meta_data
        motion_tinder.visibility = View.VISIBLE
    }
}