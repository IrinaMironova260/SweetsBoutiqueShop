package com.example.sweetsboutiqueshop.presentation.di

import androidx.room.Room
import com.example.sweetsboutiqueshop.data.localDB.DBFive
import com.example.sweetsboutiqueshop.data.repository.dataSource.*
import com.example.sweetsboutiqueshop.data.repository.dataSourceIMPL.*
import com.example.sweetsboutiqueshop.data.repository.repository.*
import com.example.sweetsboutiqueshop.domain.repository.*
import com.example.sweetsboutiqueshop.domain.useCase.*
import com.example.sweetsboutiqueshop.presentation.catalog.viewModels.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val moduleCategories = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            DBFive::class.java,
            "localDBFiveCat"
        ).build()
    }

    single { get<DBFive>().categoriesDao }

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
            DBFive::class.java,
            "localDBFiveProd"
        ).build()
    }

    single { get<DBFive>().priductsDao }


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
            DBFive::class.java,
            "localDBFiveProd"
        ).build()
    }

    single { get<DBFive>().categoriesProductsDao}


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

val moduleImageProducts = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            DBFive::class.java,
            "localDBFiveImageProduct"
        ).build()
    }

    single { get<DBFive>().imagesProductsDao}

    single<ImageProductDataSource> {
        ImageProductDataSourceIMPL(get())
    }

    single<ImageProductApiDataSource> {
        ImageProductApiDataSourceIMPL(get())
    }

    single<ImageProductCall> { ImageProductRepositopy (get(), get()) }

    single { ImageProductUseCase (get()) }

    viewModel { ImageProductViewModel (get()) }

}

val moduleImages = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            DBFive::class.java,
            "localDBFiveImage"
        ).build()
    }

    single { get<DBFive>().imagesDao}

    single<ImagesDataSouce> {
        ImagesDataSouceIMPL(get())
    }

    single<ImagesApiDataSouce> {
        ImagesApiDataSouceIMPL(get())
    }

    single<ImagesCall> { ImageRepository (get(), get()) }

    single { ImageUseCase (get()) }

    viewModel { ImagesViewModel (get()) }

}

val moduleBasket = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            DBFive::class.java,
            "localDBFiveBasket"
        ).build()
    }

    single { get<DBFive>().basketDao}

    single<BasketDataSouce> {
        BasketDataSouceIMPL(get())
    }

    single<BasketCall> { BasketRepository (get()) }
    single { BasketUseCase (get()) }
    viewModel { BasketViewModel (get()) }

}

