package com.example.sweetsboutiqueshop.presentation


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sweetsboutiqueshop.databinding.ActivityMainBinding
import com.example.sweetsboutiqueshop.presentation.catalog.viewModels.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    val categoriesViewModel: CategoriesViewModel by viewModel()
    val productsViewModel: ProductsViewModel by viewModel()
    val categoriesProductsViewModel: CategoriesProductsViewModel by viewModel()
    val imagesProductsViewModel: ImageProductViewModel by viewModel()
    val imagesViewModel: ImagesViewModel by viewModel()
    val aboutUsViewModel: AboutUsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        categoriesViewModel.migration(this)
        productsViewModel.migration(this)
        categoriesProductsViewModel.migration(this)
        imagesProductsViewModel.migration(this)
        imagesViewModel.migration(this)
        aboutUsViewModel.migration(this)

        val screenApp = Intent(this, ScreenApp::class.java)
        startActivity(screenApp)
    }



}