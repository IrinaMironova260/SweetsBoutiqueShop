package com.example.sweetsboutiqueshop.presentation.catalog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetsboutiqueshop.data.models.CategoriesModel
import com.example.sweetsboutiqueshop.databinding.CategoryItemBinding
import com.squareup.picasso.Picasso

class CategoryAdapter(private var loadProductsFromCategory: (CategoriesModel) -> Unit) :
    RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    private val categoriesList = ArrayList<CategoriesModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {

        val binding: CategoryItemBinding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(binding)
    }

    override fun getItemCount(): Int {return categoriesList.size }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(categoriesList[position], loadProductsFromCategory)
    }

    fun setList(categories: List<CategoriesModel>) {
        categoriesList.clear()
        categoriesList.addAll(categories)
    }


    class CategoryHolder(val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            model: CategoriesModel, loadProductsFromCategory: (CategoriesModel) -> Unit

        ) {
            val getImage = model.image
            Picasso.get().load(getImage).into(binding.imageCategory)
            binding.nameCategory.text = model.name

            binding.cardCategory.setOnClickListener(View.OnClickListener {
                loadProductsFromCategory(model)
            })
        }

    }
}