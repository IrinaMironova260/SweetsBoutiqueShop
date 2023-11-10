package com.example.sweetsboutiqueshop.presentation.catalog.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sweetsboutiqueshop.data.models.ProductsModel
import com.example.sweetsboutiqueshop.domain.useCase.ProductsUseCase
import kotlinx.coroutines.launch

class ProductsViewModel(private val productsUseCase: ProductsUseCase) : ViewModel() {

    val loadProducts = productsUseCase.loadProducts()

    fun loadInfoProduct(idProduct: Int): LiveData<List<ProductsModel>> {
        return productsUseCase.loadInfoProduct(idProduct)
    }

    fun migration(context: Context) = viewModelScope.launch {
        productsUseCase.startMigration(context)
    }

    fun loadProductsFromCategory (ids:List<Int>):
            LiveData<List<ProductsModel>> {
        return productsUseCase.loadProductsFromCategory(ids)
    }
}