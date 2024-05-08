package com.example.vk_list_test.presentation.fragments.product_detail.view_pager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vk_list_test.R

class DetailViewPagerAdapter : RecyclerView.Adapter<DetailPagerViewHolder>() {
    private val callback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, callback)

    fun submitList(images: List<String>) {
        differ.submitList(images)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailPagerViewHolder {
        return DetailPagerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product_viewpager_image, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: DetailPagerViewHolder, position: Int) {
        val url = differ.currentList[position]
        holder.bind(url)
    }
}