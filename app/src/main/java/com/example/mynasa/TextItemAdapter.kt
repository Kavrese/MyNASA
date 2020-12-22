package com.example.mynasa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TextItemAdapter(val list: MutableList<Asteroid>) : RecyclerView.Adapter<TextItemAdapter.TextViewHolder>() {

    class TextViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val arg1 = itemView.findViewById<TextView>(R.id.arg1)
        val arg2 = itemView.findViewById<TextView>(R.id.arg2)
        val arg3 = itemView.findViewById<TextView>(R.id.arg3)
        val tint = itemView.findViewById<TextView>(R.id.tint)
        val info = itemView.findViewById<TextView>(R.id.info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rec_text, parent, false)
        return TextViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.tint.text = list[position].name
        holder.info.text = list[position].nasa_jpl_url
        holder.arg1.text = list[position].estimated_diameter?.meter!!.estimated_diameter_max
        holder.arg2.text = list[position].close_approach_data!!.close_approach_date
        holder.arg3.text = list[position].close_approach_data!!.miss_distance!!.kilometers
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}