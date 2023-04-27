package com.example.sweetsboutiqueshop.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sweetsboutiqueshop.data.models.CategoriesProductsModel

interface CategoriesProductsCall {

    fun loadCategoriesProducts(idProduct:Int): LiveData<List<CategoriesProductsModel>>
    suspend fun startMigration(context: Context)
}