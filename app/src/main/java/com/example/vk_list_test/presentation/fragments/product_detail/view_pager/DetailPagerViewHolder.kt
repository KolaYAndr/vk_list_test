package com.example.vk_list_test.presentation.fragments.product_detail.view_pager

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vk_list_test.R

class DetailPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val productImage = itemView.findViewById<ImageView>(R.id.detailViewPagerImage)

    fun bind(url: String) {
        Glide.with(itemView)
            .load(url)
            .into(productImage)
    }
}