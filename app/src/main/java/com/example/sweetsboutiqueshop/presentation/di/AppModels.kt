package com.example.sweetsboutiqueshop.presentation.di

import androidx.room.Room
import com.example.sweetsboutiqueshop.data.localDB.DBThree
import com.example.sweetsboutiqueshop.data.localDB.DBTwo
import com.example.sweetsboutiqueshop.data.repository.dataSource.*
import com.example.sweetsboutiqueshop.data.repository.dataSourceIMPL.*
import com.example.sweetsboutiqueshop.data.repository.repository.CategoriesProductsRepository
import com.example.sweetsboutiqueshop.data.repository.repository.CategoriesRepository
import com.example.sweetsboutiqueshop.data.repository.repository.ProductsRepository
import com.example.sweetsboutiqueshop.domain.repository.CategoriesProductsCall
import com.example.sweetsboutiqueshop.domain.repository.CategoryCall
import com.example.sweetsboutiqueshop.domain.repository.ProductsCall
import com.example.sweetsboutiqueshop.domain.useCase.CategoriesProductsUseCase
import com.example.sweetsboutiqueshop.domain.useCase.CategoriesUseCase
import com.example.sweetsboutiqueshop.domain.useCase.ProductsUseCase
import com.example.sweetsboutiqueshop.presentation.catalog.viewModels.CategoriesProductsViewModel
import com.example.sweetsboutiqueshop.presentation.catalog.viewModels.CategoriesViewModel
import com.example.sweetsboutiqueshop.presentation.catalog.viewModels.ProductsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val moduleCategories = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            DBThree::class.java,
            "localDBThreeCat"
        ).build()
    }

    single { get<DBThree>().categoriesDao }

    single<CategoriesDataSourse> {
        CategoriesDataSourseIMPL(get())
    }

    single<CategoriesApiDataSourse> {
        CategoriesApiDataSourceIMPL(get())
    }

    single<CategoryCall> { CategoriesRepository(get(), get()) }
    single { CategoriesUseCase(get()) }
    viewModel { CategoriesViewModel(get()) }

}


val moduleProducts = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            DBThree::class.java,
            "localDBThreeProd"
        ).build()
    }

    single { get<DBThree>().priductsDao }


    single<ProductsDataSourse> {
        ProductsDataSourseIMPL(get())
    }

    single<ProductsApiDataSourse> {
        ProductsApiDataSourseIMPL(get())
    }

    single<ProductsCall> { ProductsRepository(get(), get()) }
    single { ProductsUseCase(get()) }
    viewModel { ProductsViewModel(get()) }

}

val moduleCategoriesProducts = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            DBThree::class.java,
            "localDBThreeProd"
        ).build()
    }

    single { get<DBThree>().categoriesProductsDao}


    single<CategoriesProductsDataSource> {
        CategoriesProductsDataSourceIMPL(get())
    }

    single<CategoriesProductsApiDataSource> {
        CategoriesProductsApiDataSourceIMPL(get())
    }

    single<CategoriesProductsCall> { CategoriesProductsRepository(get(), get()) }
    single { CategoriesProductsUseCase(get()) }
    viewModel { CategoriesProductsViewModel(get()) }

}
