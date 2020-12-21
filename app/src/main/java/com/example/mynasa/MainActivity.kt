package com.example.mynasa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment(FragmentNews())
        setFocusScrollWindow(news_win)
        news_win.setOnClickListener(this)
        earth_win.setOnClickListener(this)
        asteroid_win.setOnClickListener(this)
        mars_win.setOnClickListener(this)
    }

    private fun setFragment (fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fr = fragmentManager.beginTransaction()
        fr.replace(R.id.fram, fragment)
        fr.commit()
    }

    private fun setFocusScrollWindow(view: TextView){
        news_win.background = resources.getDrawable(R.drawable.shape_oval)
        mars_win.background = resources.getDrawable(R.drawable.shape_oval)
        earth_win.background = resources.getDrawable(R.drawable.shape_oval)
        asteroid_win.background = resources.getDrawable(R.drawable.shape_oval)
        view.background = resources.getDrawable(R.drawable.shape_oval_grey)
    }

    override fun onClick(p0: View?) {
        setFocusScrollWindow(p0 as TextView)
        when (p0){
            news_win -> setFragment(FragmentNews())
            mars_win -> setFragment(FragmentMars())
            earth_win -> setFragment(FragmentEather())
            asteroid_win -> setFragment(FragmentAsteroid())
        }
    }
}