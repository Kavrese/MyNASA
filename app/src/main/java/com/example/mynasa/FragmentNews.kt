package com.example.mynasa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentNews(val list: MutableList<ModelNews>): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}