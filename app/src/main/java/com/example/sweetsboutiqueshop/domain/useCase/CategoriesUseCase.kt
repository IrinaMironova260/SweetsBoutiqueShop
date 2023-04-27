package com.example.sweetsboutiqueshop.domain.useCase

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sweetsboutiqueshop.data.models.CategoriesModel
import com.example.sweetsboutiqueshop.domain.repository.CategoryCall

class CategoriesUseCase(private val call: CategoryCall) {

    fun loadCategories(): LiveData<List<CategoriesModel>>{
        return call.loadCategories()
    }

    fun loadCategoriesProduct(idsCategories:List<Int>): LiveData<List<CategoriesModel>>{
        return call.loadCategoriesProduct(idsCategories)
    }


    suspend fun startMigration(context: Context){
        call.startMigration(context)
    }
}