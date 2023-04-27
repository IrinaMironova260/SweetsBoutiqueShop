package com.example.sweetsboutiqueshop.presentation.catalog.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sweetsboutiqueshop.data.models.CategoriesModel
import com.example.sweetsboutiqueshop.domain.useCase.CategoriesUseCase
import kotlinx.coroutines.launch

class CategoriesViewModel (private val categoriesUseCase: CategoriesUseCase) : ViewModel() {

    val loadCategory = categoriesUseCase.loadCategories()

   fun loadCategoriesProduct (idsCategories:List<Int>): LiveData<List<CategoriesModel>> {
        return categoriesUseCase.loadCategoriesProduct(idsCategories)
    }

    fun migration(context: Context) = viewModelScope.launch {
        categoriesUseCase.startMigration(context)
    }
}