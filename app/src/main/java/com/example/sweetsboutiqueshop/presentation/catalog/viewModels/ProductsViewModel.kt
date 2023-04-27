package com.example.sweetsboutiqueshop.presentation.catalog.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sweetsboutiqueshop.domain.useCase.ProductsUseCase
import kotlinx.coroutines.launch

class ProductsViewModel(private val productsUseCase: ProductsUseCase) : ViewModel() {

    val loadProducts = productsUseCase.loadProducts()

    fun migration(context: Context) = viewModelScope.launch {
        productsUseCase.startMigration(context)
    }
}