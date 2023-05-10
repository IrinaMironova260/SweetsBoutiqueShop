package com.example.sweetsboutiqueshop.presentation


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sweetsboutiqueshop.R
import com.example.sweetsboutiqueshop.databinding.ActivityMainBinding
import com.example.sweetsboutiqueshop.presentation.basket.Basket
import com.example.sweetsboutiqueshop.presentation.catalog.Catalog
import com.example.sweetsboutiqueshop.presentation.catalog.viewModels.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    val categoriesViewModel: CategoriesViewModel by viewModel()
    val productsViewModel: ProductsViewModel by viewModel()
    val categoriesProductsViewModel: CategoriesProductsViewModel by viewModel()
    val imagesProductsViewModel: ImageProductViewModel by viewModel()
    val imagesViewModel: ImagesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        categoriesViewModel.migration(this)
        productsViewModel.migration(this)
        categoriesProductsViewModel.migration(this)
        imagesProductsViewModel.migration(this)
        imagesViewModel.migration(this)

        supportFragmentManager.beginTransaction().replace(R.id.mainContent, Catalog()).commit()

        binding?.bottomNav?.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.homeBottomMainMenu -> supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContent, Catalog()).commit()
                R.id.coffeeBottomMainMenu -> supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContent, Basket()).commit()
//              R.id.cardBottomMainMenu -> supportFragmentManager.beginTransaction()
//                    .replace(R.id.mainContent, Card()).commit()
//              R.id.accountBottomMainMenu -> supportFragmentManager.beginTransaction()
//                    .replace(R.id.mainContent, Account()).commit()
            }
            return@setOnItemSelectedListener true

        }
        binding?.bottomNav?.selectedItemId = R.id.homeBottomMainMenu
    }


}