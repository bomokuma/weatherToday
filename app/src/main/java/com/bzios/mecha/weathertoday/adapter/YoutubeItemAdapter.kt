package com.bzios.mecha.weathertoday.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bzios.mecha.weathertoday.R
import com.bzios.mecha.weathertoday.model.Youtube

class YoutubeItemAdapter(private val context: Context) :
    ListAdapter<Youtube, YoutubeItemViewHolder>(YoutubeDiffUtil()) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeItemViewHolder {
        val view = layoutInflater.inflate(R.layout.item_main_weather_view, parent, false)
        return YoutubeItemViewHolder(context, view)
    }

    override fun onBindViewHolder(holder: YoutubeItemViewHolder, position: Int) {
        val youtube = getItem(position)
        holder.initView(youtube, position)
    }
}