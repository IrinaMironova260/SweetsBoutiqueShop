package com.example.sweetsboutiqueshop.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sweetsboutiqueshop.data.models.ProductsModel

interface ProductsCall {
    fun loadProducts(): LiveData<List<ProductsModel>>
    suspend fun startMigration(context: Context)
}