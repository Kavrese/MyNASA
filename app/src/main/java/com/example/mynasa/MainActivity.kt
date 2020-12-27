package com.example.mynasa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    var now_Fragment_bottom: Fragment? = BottomMain()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment(BottomMain())
    }

    private fun setFragment (fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fr = fragmentManager.beginTransaction()
        fr.add(R.id.frame_bottom, fragment)
        fr.hide(now_Fragment_bottom!!)
        fr.commit()
        now_Fragment_bottom = fragment
    }
}