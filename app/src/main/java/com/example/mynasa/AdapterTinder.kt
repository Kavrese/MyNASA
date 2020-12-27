package com.example.mynasa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdapterTinder(var list: MutableList<ModelTinder>): RecyclerView.Adapter<AdapterTinder.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.img)
        val meta_data = itemView.findViewById<TextView>(R.id.text)
        val name = itemView.findViewById<TextView>(R.id.name)
        val good = itemView.findViewById<ImageView>(R.id.good)
        val bad = itemView.findViewById<ImageView>(R.id.bad)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tinder, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get()
            .load(list[position].img)
            .into(holder.img)

        holder.meta_data.text = list[position].meta_data
        holder.name.text = list[position].name
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}