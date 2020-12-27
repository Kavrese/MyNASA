package com.example.mynasa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.bottom_main.*

class BottomMain: Fragment(), View.OnClickListener {
    var now_Fragment_main: Fragment? = FragmentNews()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setFocusScrollWindow(news_win)
        setFragment(FragmentNews())
        news_win.setOnClickListener(this)
        earth_win.setOnClickListener(this)
        asteroid_win.setOnClickListener(this)
        mars_win.setOnClickListener(this)
    }

    private fun setFocusScrollWindow(view: TextView){
        news_win.background = resources.getDrawable(R.drawable.shape_oval)
        mars_win.background = resources.getDrawable(R.drawable.shape_oval)
        earth_win.background = resources.getDrawable(R.drawable.shape_oval)
        asteroid_win.background = resources.getDrawable(R.drawable.shape_oval)
        view.background = resources.getDrawable(R.drawable.shape_oval_grey)
    }

    private fun setFragment (fragment: Fragment){
        val fragmentManager = childFragmentManager
        val fr = fragmentManager.beginTransaction()
        fr.add(R.id.fram, fragment)
        fr.hide(now_Fragment_main!!)
        fr.commit()
        now_Fragment_main = fragment
    }

    override fun onClick(p0: View?) {
        setFocusScrollWindow(p0 as TextView)
        when (p0){
            news_win -> setFragment(FragmentNews())
            mars_win -> setFragment(FragmentMars())
            earth_win -> setFragment(FragmentEarth())
            asteroid_win -> setFragment(FragmentAsteroid())
        }
    }
}