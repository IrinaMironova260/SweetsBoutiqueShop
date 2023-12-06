package com.example.sweetsboutiqueshop.presentation.catalog.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetsboutiqueshop.data.models.ProductsModel
import com.example.sweetsboutiqueshop.databinding.ProductItemBinding
import com.squareup.picasso.Picasso

class ProductAdapter(private var startCardProduct: (ProductsModel) -> Unit) :
    RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    private val productsList = ArrayList<ProductsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding: ProductItemBinding =
            ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductHolder(binding)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(productsList[position], startCardProduct)
    }

    fun setList(products: List<ProductsModel>) {
        productsList.clear()
        productsList.addAll(products)
    }


    class ProductHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private var context: Context? = null

        fun bind(
            model: ProductsModel,
            startCardProduct: (ProductsModel) -> Unit
        ) {

            val getImage = model.mainImage
            Picasso.get().load(getImage).into(binding.imageProduct)

            binding.nameProduct.text = model.name

            val price = model.price
            val salePrice = model.salePrice


            if (model.salePrice == 0) {
                binding.priceProduct.text = "Стоимость $price рублей."
            } else {
                binding.priceProduct.text = "Цена со скидкой: $salePrice руб."
            }

//            if (model.salePrice != 0) {
//                binding.priceProduct.text = "Цена со скидкой: $salePrice руб."
//            } else {
//                binding.salePriceProduct.text = ""
//            }

            val delivery = model.deliveryTime
            binding.deliveryTimeProduct.text = "Доставка через $delivery"

            binding.goToCardProdPrev.setOnClickListener(View.OnClickListener {
                startCardProduct(model)
            })
        }
    }

}