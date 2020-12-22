package com.example.mynasa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.item_earth.*

class FragmentAsteroid: Fragment() {
    val list: MutableList<Asteroid> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_asteroid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rec_earth.apply {
           // adapter =
            layoutManager = LinearLayoutManager(requireContext())
        }
        getAsteroids()
    }

    private fun getAsteroids(){

    }
}