package com.example.sweetsboutiqueshop.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sweetsboutiqueshop.data.models.CategoriesModel

interface CategoryCall {

    fun loadCategories(): LiveData<List<CategoriesModel>>
    fun loadCategoriesProduct(idsCategories:List<Int>): LiveData<List<CategoriesModel>>
    suspend fun startMigration(context: Context)
}