package com.example.sweetsboutiqueshop.presentation.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetsboutiqueshop.R
import com.example.sweetsboutiqueshop.databinding.CardProductBinding
import com.example.sweetsboutiqueshop.presentation.catalog.adapter.ImagesProductAdapter
import com.example.sweetsboutiqueshop.presentation.catalog.viewModels.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class CardProduct : BottomSheetDialogFragment(), View.OnClickListener {

    private var activePrice: Int? = null
    private var binding: CardProductBinding? = null
    private var idProduct: Int? = null
    val productsViewModel: ProductsViewModel by viewModel()
    val categoriesProductsViewModel: CategoriesProductsViewModel by viewModel()
    val categoriesViewModel: CategoriesViewModel by viewModel()
    val imagesProductsViewModel: ImageProductViewModel by viewModel()
    val imagesViewModel: ImagesViewModel by viewModel()
    val basketViewModel: BasketViewModel by viewModel()
    private var adapter: ImagesProductAdapter? = null
    var countToBasket = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CardProductBinding.inflate(layoutInflater, container, false)

        binding?.salePriceProductCardProduct?.text = "????"

        idProduct = arguments?.getInt("idProduct")

        initRecyclerImagesProducts()

        loadCategories()
        loadInfo()
        loadImages()
        conditionAddToBasket()

        binding?.addToBasketCardProduct?.setOnClickListener(this)

        return binding?.root
    }

    private fun initRecyclerImagesProducts() {
        binding?.recyclerImagesCardProduct?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = ImagesProductAdapter()
        binding?.recyclerImagesCardProduct?.adapter = adapter
    }

    private fun loadInfo() {

        productsViewModel.loadInfoProduct(idProduct!!).observe(viewLifecycleOwner, Observer {

            binding?.nameProductCardProduct?.text = it.last().name
            binding?.descriptionProductCardProduct?.text = it.last().description
            binding?.priceProductCardProduct?.text =
                it.last().price.toString() + getString(R.string.currency)
            binding?.salePriceProductCardProduct?.text =
                it.last().salePrice.toString() + getString(R.string.currency)
            if (it.last().salePrice > 0) {
                activePrice = it.last().salePrice
            } else {
                activePrice = it.last().price
            }

            binding?.deliveryTime?.text = getString(R.string.deliveryTime) + it.last().deliveryTime
        })
    }

    private fun loadCategories() {
        categoriesProductsViewModel.loadCategoryProducts(idProduct!!)
            .observe(viewLifecycleOwner, Observer {
                val data: List<Int> = it.map {
                    it.categoryId
                }
                categoriesViewModel.loadCategoriesProduct(data)
                    .observe(viewLifecycleOwner, Observer {
                        binding?.categoriesProductCardProduct?.text =
                            it.map { it.name }.joinToString(", ")
                    })
            })
    }

    private fun loadImages() {
        imagesProductsViewModel.loadImagesProduct(idProduct!!)
            .observe(viewLifecycleOwner, Observer {
                val data: List<Int> = it.map {
                    it.imageId
                }
                imagesViewModel.loadImagesProduct(data).observe(viewLifecycleOwner, Observer {
                    adapter?.setList(it)
                    adapter?.notifyDataSetChanged()
                })
            })
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.addToBasketCardProduct -> {
                idProduct?.let {
                    binding?.nameProductCardProduct?.text?.toString()
                        ?.let { it1 ->
                            activePrice?.let { it2 -> basketViewModel.startInsert(it1, it2, 1, it) }
                        }
                    conditionAddToBasket()
                }
            }
        }
    }

    private fun conditionAddToBasket() {
        idProduct?.let {
            basketViewModel.loadProductToBasket(it.toInt()).observe(viewLifecycleOwner, Observer {
                binding?.nameProductCardProduct?.text = it.count().toString()

                if (it.count() > 0) {
                    //add
                    countToBasket = it.count()
                    binding?.addToBasketCardProduct?.setImageResource(R.drawable.delete)
                } else {
                    //remove
                    countToBasket = 0
                    binding?.addToBasketCardProduct?.setImageResource(R.drawable.basket_small)
                }
            })
        }
    }
}