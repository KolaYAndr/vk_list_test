package com.example.vk_list_test.presentation.fragments.product_list.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vk_list_test.R
import com.example.vk_list_test.domain.product.model.Product

class ProductAdapter(callback: DiffUtil.ItemCallback<Product> = productCallback) :
    PagingDataAdapter<Product, ProductAdapter.ProductViewHolder>(callback) {

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)!!
        holder.bind(product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_item_view, parent, false)
        )

    private var onItemClickListener: ((Product) -> Unit)? = null

    fun setOnItemClickListener(listener: (Product) -> Unit) {
        onItemClickListener = listener
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productImage = itemView.findViewById<ImageView>(R.id.productImage)
        private val productName = itemView.findViewById<TextView>(R.id.productName)
        private val productDescription = itemView.findViewById<TextView>(R.id.productDescription)
        private val productPrice = itemView.findViewById<TextView>(R.id.productPrice)

        fun bind(product: Product) {
            itemView.apply {
                Glide.with(this)
                    .load(product.thumbnail)
                    .into(productImage)

                productName.text = product.title
                productDescription.text = product.description
                productPrice.text =
                    context.resources.getString(R.string.product_price, product.price)

                setOnClickListener {
                    onItemClickListener?.let { it(product) }
                }
            }
        }
    }
}