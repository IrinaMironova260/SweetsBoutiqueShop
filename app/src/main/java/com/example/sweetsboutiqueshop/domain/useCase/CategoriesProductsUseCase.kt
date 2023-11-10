package com.example.sweetsboutiqueshop.domain.useCase

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sweetsboutiqueshop.data.models.CategoriesProductsModel
import com.example.sweetsboutiqueshop.domain.repository.CategoriesProductsCall

class CategoriesProductsUseCase (private val call: CategoriesProductsCall) {

    fun loadProductsCategory(idProduct:Int): LiveData<List<CategoriesProductsModel>> {
        return call.loadProductsCategory(idProduct)
    }

    suspend fun startMigration(context: Context){
        call.startMigration(context)
    }
}