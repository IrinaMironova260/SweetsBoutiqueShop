package com.example.sweetsboutiqueshop.presentation.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetsboutiqueshop.data.models.ProductsModel
import com.example.sweetsboutiqueshop.databinding.CatalogBinding
import com.example.sweetsboutiqueshop.presentation.catalog.adapter.CategoryAdapter
import com.example.sweetsboutiqueshop.presentation.catalog.adapter.ProductAdapter
import com.example.sweetsboutiqueshop.presentation.catalog.viewModels.CategoriesViewModel
import com.example.sweetsboutiqueshop.presentation.catalog.viewModels.ProductsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class Catalog : Fragment() {
    private var binding: CatalogBinding? = null

    private var categoryAdapter: CategoryAdapter? = null
    private var productAdapter: ProductAdapter? = null

    val categoriesViewModel: CategoriesViewModel by viewModel()
    val productsViewModel: ProductsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CatalogBinding.inflate(inflater, container, false)

        initRecyclerCategories()
        initRecyclerProducts()
        loadCategories()
        loadProducts()

        return binding?.root
    }

    private fun initRecyclerCategories() {
        binding?.recyclerCategories?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter = CategoryAdapter()
        binding?.recyclerCategories?.adapter = categoryAdapter
    }

    private fun initRecyclerProducts() {
        binding?.recyclerProducts?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        productAdapter = ProductAdapter({ model: ProductsModel -> startCardProduct(model) })
        binding?.recyclerProducts?.adapter = productAdapter
    }

    private fun loadCategories() {
        categoriesViewModel.loadCategory.observe(viewLifecycleOwner, Observer {
            categoryAdapter?.setList(it)
            categoryAdapter?.notifyDataSetChanged()
        })
    }

    private fun loadProducts() {
        productsViewModel.loadProducts.observe(viewLifecycleOwner, Observer {
            productAdapter?.setList(it)
            productAdapter?.notifyDataSetChanged()
        })
    }

    private fun startCardProduct(model: ProductsModel) {
        var bundle = Bundle()
        bundle.putInt("idProduct", model.id)
        var card = CardProduct()
        card.arguments = bundle
        card.show(childFragmentManager, "cardProduct")
    }


}