package com.example.mynasa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MultiAdapterRec(private val list: MutableList<ModelNews>): RecyclerView.Adapter<MultiAdapterRec.MyViewHolder>() {
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val img_news = itemView.findViewById<ImageView>(R.id.img_news)
        val info_news = itemView.findViewById<TextView>(R.id.info_news)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_rec_news, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get()
            .load(list[position].hdurl)
            .placeholder(R.drawable.load)
            .into(holder.img_news)
        holder.info_news.text = list[position].title
    }
}