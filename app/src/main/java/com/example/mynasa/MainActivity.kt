package com.example.mynasa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment(FragmentNews(getNews()))
    }

    fun getNews (): MutableList<ModelNews>{
        var list = mutableListOf<ModelNews>()

        return list
    }

    fun setFragment (fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fr = fragmentManager.beginTransaction()
        fr.replace(R.id.fram, fragment)
    }
}