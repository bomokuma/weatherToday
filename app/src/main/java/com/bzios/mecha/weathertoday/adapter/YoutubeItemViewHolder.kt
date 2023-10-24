package com.bzios.mecha.weathertoday.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bzios.mecha.weathertoday.databinding.ItemMainWeatherViewBinding
import com.bzios.mecha.weathertoday.model.Youtube

class YoutubeItemViewHolder(private val context: Context, itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private val itemBinding get() = ItemMainWeatherViewBinding.bind(itemView)
    fun initView(youtube: Youtube, position: Int) {
        itemBinding.itemMainWeatherTitleTextLabel.text = youtube.title
        itemBinding.itemMainWeatherSubtitleTextLabel.text = youtube.subtitle
        Glide.with(context).load(youtube.youtubeImage).into(itemBinding.itemMainWeatherImageView);
    }
}