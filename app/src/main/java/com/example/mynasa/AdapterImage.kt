package com.example.mynasa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdapterImage(var list: MutableList<ModelImage>): RecyclerView.Adapter<AdapterImage.AdapterViewHolder>() {

    class AdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.img)
        val arg1 = itemView.findViewById<TextView>(R.id.arg1)
        val arg2 = itemView.findViewById<TextView>(R.id.arg2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rec_image, parent, false)
        return AdapterViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        Picasso.get()
            .load(list[position].img)
            .into(holder.img)

        if (list[position].arg1 != null) {
            holder.arg1.text = list[position].arg1
        }else{
            holder.arg1.visibility = View.GONE
        }

        if (list[position].arg2 != null) {
            holder.arg2.text = list[position].arg2
        }else{
            holder.arg2.visibility = View.GONE
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}