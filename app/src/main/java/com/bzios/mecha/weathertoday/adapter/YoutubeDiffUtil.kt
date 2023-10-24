package com.bzios.mecha.weathertoday.adapter

import androidx.recyclerview.widget.DiffUtil
import com.bzios.mecha.weathertoday.model.Youtube

class YoutubeDiffUtil : DiffUtil.ItemCallback<Youtube>() {

    override fun areItemsTheSame(oldItem: Youtube, newItem: Youtube): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Youtube, newItem: Youtube): Boolean = oldItem.id == newItem.id
}