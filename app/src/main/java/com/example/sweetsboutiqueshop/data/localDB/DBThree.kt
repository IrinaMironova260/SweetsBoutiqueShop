package com.example.sweetsboutiqueshop.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sweetsboutiqueshop.data.models.CategoriesModel
import com.example.sweetsboutiqueshop.data.models.CategoriesProductsModel
import com.example.sweetsboutiqueshop.data.models.ProductsModel

@Database(entities = [
    ProductsModel::class,
    CategoriesModel::class,
    CategoriesProductsModel::class], version = 1)

abstract class DBThree: RoomDatabase() {
    abstract val priductsDao: ProductsDao
    abstract val categoriesDao: CategoriesDao
    abstract val categoriesProductsDao: CategoriesProductsDao
}