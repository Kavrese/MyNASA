package com.example.mynasa

import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapterRec(private val list: MutableList<ModelNews>): RecyclerView.Adapter<NewsAdapterRec.MyViewHolder>() {
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val img_news = itemView.findViewById<ImageView>(R.id.img_news)
        val info_news = itemView.findViewById<TextView>(R.id.info_news)
        val tint_news = itemView.findViewById<TextView>(R.id.tint_news)
        val media_news = itemView.findViewById<TextView>(R.id.type_media)
        val date_news = itemView.findViewById<TextView>(R.id.date_media)
        val exo = itemView.findViewById<com.google.android.exoplayer2.ui.PlayerView>(R.id.video_player)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_rec_news, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (list[position].media_type == "image") {
            holder.exo.visibility = View.GONE
            Picasso.get()
                .load(list[position].hdurl)
                .placeholder(R.drawable.load)
                .into(holder.img_news)
        }else{
            holder.exo.visibility = View.VISIBLE
            val video = SimpleExoPlayer.Builder(holder.itemView.context).build()
            holder.exo.player = video
            video.prepare(ProgressiveMediaSource.Factory(DefaultDataSourceFactory(holder.itemView.context, "sample")).createMediaSource(
                Uri.parse(list[position].url)))

        }
        holder.tint_news.text = list[position].title
        if (list[position].explanation!!.length > 128) {
            list[position].explanation = list[position].explanation!!.substring(0, 128) + "..."
        }
        holder.info_news.text = list[position].explanation
        holder.media_news.text = list[position].media_type
        holder.date_news.text = list[position].date
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}