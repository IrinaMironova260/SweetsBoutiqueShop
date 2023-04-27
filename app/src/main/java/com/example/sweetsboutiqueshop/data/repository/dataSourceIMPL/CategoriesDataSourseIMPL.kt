package com.example.sweetsboutiqueshop.data.repository.dataSourceIMPL

import androidx.lifecycle.LiveData
import com.example.sweetsboutiqueshop.data.localDB.CategoriesDao
import com.example.sweetsboutiqueshop.data.models.CategoriesModel
import com.example.sweetsboutiqueshop.data.repository.dataSource.CategoriesDataSourse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesDataSourseIMPL(private val dao: CategoriesDao): CategoriesDataSourse {

    override fun insert(categoriesModel: CategoriesModel) {
        CoroutineScope(Dispatchers.IO).launch { dao.insert(categoriesModel) }
    }

    override fun loadAllCategories(): LiveData<List<CategoriesModel>> {
        return dao.getAllCategories()
    }

    override fun loadCategoriesProduct(idsCategories:List<Int>): LiveData<List<CategoriesModel>>{
        return dao.loadCategoriesProduct(idsCategories)
    }

    override suspend fun clear() {
        CoroutineScope(Dispatchers.IO).launch { dao.clear() }
    }
}