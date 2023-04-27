package com.example.sweetsboutiqueshop.data.repository.dataSourceIMPL

import android.content.Context
import android.widget.Toast
import com.example.sweetsboutiqueshop.data.api.ApiClient
import com.example.sweetsboutiqueshop.data.models.CategoriesProductsApiModel
import com.example.sweetsboutiqueshop.data.models.CategoriesProductsModel
import com.example.sweetsboutiqueshop.data.repository.dataSource.CategoriesProductsApiDataSource
import com.example.sweetsboutiqueshop.data.repository.dataSource.CategoriesProductsDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesProductsApiDataSourceIMPL (private val categoriesProductsDataSource
: CategoriesProductsDataSource):  CategoriesProductsApiDataSource {

    override fun startMigration (context: Context) {

        val call = ApiClient.instance?.api?.loadCatigoriesProducts()
        call?.enqueue(object: Callback<ArrayList<CategoriesProductsApiModel>> {
            override fun onResponse(
                call: Call<ArrayList<CategoriesProductsApiModel>>,
                response: Response<ArrayList<CategoriesProductsApiModel>>
            ) {
                var loadCategoriesProducts: ArrayList<CategoriesProductsApiModel>? = null

                loadCategoriesProducts?.clear()
                loadCategoriesProducts =
                    (response.body() as ArrayList<CategoriesProductsApiModel>?)!!

                for (audit in loadCategoriesProducts) {

                    audit.id?.let {
                        CategoriesProductsModel(
                            it,
                            audit.productId!!,
                            audit.categoryId!!
                        )
                    }?.let {
                        categoriesProductsDataSource.insert(
                            it
                        )
                    }
                }
                Toast.makeText(context, "ЗАГРУЗКА", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(
                call: Call<ArrayList<CategoriesProductsApiModel>>,
                t: Throwable
            ) {
                Toast.makeText(context, "ОШИБКА! ВКЛЮЧИТЕ ИНТЕРНЕТ!", Toast.LENGTH_SHORT).show()
            }
        })

    }

}
