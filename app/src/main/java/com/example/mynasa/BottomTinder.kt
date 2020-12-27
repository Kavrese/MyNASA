package com.example.mynasa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.bottom_tinder.*

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
        list.add(ModelTinder("name", "https://avatars.mds.yandex.net/get-zen_doc/59923/pub_59f87e499b403c8ce7df3a61_59f87f361410c3732b5337bf/scale_1200", "good art weqlkvnqovnoinobienoiqoivboqvobqveiobvbqvrhwhewhwhewebesebgwgregrwgwgrwgwewg"))
        rec_tinder.apply {
            layoutManager =  LinearLayoutManager(activity!!.applicationContext, LinearLayoutManager.HORIZONTAL, false)
            adapter = AdapterTinder(list)
        }
    }
}