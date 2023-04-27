package com.example.sweetsboutiqueshop.presentation.catalog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetsboutiqueshop.data.models.ProductsModel
import com.example.sweetsboutiqueshop.databinding.ProductItemBinding
import com.squareup.picasso.Picasso

class ProductAdapter(private var setCategory:(ProductsModel) -> Unit)
    :  RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    private val productsList = ArrayList<ProductsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding: ProductItemBinding = ProductItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductHolder(binding)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(productsList[position], setCategory)
    }

    fun setList(coffeeList: List<ProductsModel>) {
        productsList.clear()
        productsList.addAll(coffeeList)
    }

    class ProductHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            model: ProductsModel,
            setCategory: (ProductsModel) -> Unit
        ) {
            val getImage = model.mainImage

            Picasso.get().load(getImage).into(binding.imageProduct)
            binding.nameProduct.text = model.name
            binding.categoryProduct.text = "description category"
            val price = model.price
            binding.priceProduct.text = price.toString()

            if (model.salePrice != 0) {
                val salePrice = model.salePrice
                binding.salePriceProduct.text = "$salePrice руб."
            } else {
                binding?.salePriceProduct?.text = ""
            }

            setCategory(model)
        }
    }

}