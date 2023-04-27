package com.example.sweetsboutiqueshop.data.api

import com.example.sweetsboutiqueshop.data.models.CategoriesApiModel
import com.example.sweetsboutiqueshop.data.models.CategoriesProductsApiModel
import com.example.sweetsboutiqueshop.data.models.ProductsApiModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("loadCategories.php")
    fun loadCategories(): Call<ArrayList<CategoriesApiModel>>

    @GET("loadProducts.php")
    fun loadProducts(): Call<ArrayList<ProductsApiModel>>

    @GET("loadCategoriesProducts.php")
    fun loadCatigoriesProducts(): Call<ArrayList<CategoriesProductsApiModel>>
}