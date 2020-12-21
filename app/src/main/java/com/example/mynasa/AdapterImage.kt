package com.example.mynasa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdapterImage(var list: MutableList<Photos>): RecyclerView.Adapter<AdapterImage.AdapterViewHolder>() {

    class AdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.img)
        val date = itemView.findViewById<TextView>(R.id.arg1)
        val sol = itemView.findViewById<TextView>(R.id.arg2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rec_image, parent, false)
        return AdapterViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        Picasso.get()
            .load(list[position].img_src)
            .into(holder.img)
        holder.sol.text = list[position].sol.toString()
        holder.date.text = list[position].earth_date
    }
}