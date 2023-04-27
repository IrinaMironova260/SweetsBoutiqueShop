package com.example.sweetsboutiqueshop.presentation.catalog.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sweetsboutiqueshop.data.models.CategoriesProductsModel
import com.example.sweetsboutiqueshop.domain.useCase.CategoriesProductsUseCase
import kotlinx.coroutines.launch

class CategoriesProductsViewModel  (private val useCase: CategoriesProductsUseCase)
    : ViewModel() {

    fun loadCategoryProducts(idProduct: Int): LiveData<List<CategoriesProductsModel>>{
        return useCase.loadCategoriesProduct(idProduct)
    }

    fun migration(context: Context) = viewModelScope.launch {
        useCase.startMigration(context)
    }
}