package com.rzerocorp.melidemo.presentation.products.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rzerocorp.melidemo.R
import com.rzerocorp.melidemo.data.models.Product
import com.rzerocorp.melidemo.databinding.ProductItemBinding
import com.rzerocorp.melidemo.utils.convertToHttps
import com.rzerocorp.melidemo.utils.formatAsCurrency

class ProductAdapter : PagingDataAdapter<Product, ProductAdapter.ViewHolder>(ProductDiffCallback()) {

    inner class ViewHolder(private val binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            val visibility = if(!product.shipping.free_shipping) { View.INVISIBLE } else { View.VISIBLE }

            binding.txtTitle.text = product.title
            binding.txtPrice.text = product.price.formatAsCurrency()
            binding.txtInstallments.text = String.format("%d x %s", product.installments?.quantity, product.installments?.amount?.formatAsCurrency())
            binding.txtHasFreeShipping.text = binding.root.context.getString(R.string.free_shipping)

            binding.txtHasFreeShipping.visibility = visibility

            Glide.with(binding.root)
                .load(product.thumbnail.convertToHttps())
                .fitCenter()
                .into(binding.ivProduct)

            binding.root.setOnClickListener {
                val bundle = bundleOf("id" to product.id)
                binding.root.findNavController().navigate(R.id.action_productsFragment_to_productDetailFragment, bundle)
            }
        }
    }

    private class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}