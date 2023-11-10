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
    val compareViewModel: CompareViewModel by viewModel()
    val favoriteViewModel: FavoritesViewModel by viewModel()

    private var adapter: ImagesProductAdapter? = null

    var countToBasket = 0
    var countToFavorites = 0
    var countToCompare = 0

    var mainImage: String? = null

    var priceProduct = 0
    var salePriceProduct = 0

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
        conditionAddToFavorites()
        conditionAddToCompare()

        binding?.actionBasketCardProduct?.setOnClickListener(this)
        binding?.addToFavoritesCardProduct?.setOnClickListener(this)

        binding?.addToCompareCardProduct?.setOnClickListener(this)

        if (countToFavorites > 0) {
            binding?.addToFavoritesCardProduct?.setImageResource(R.drawable.favorites)
        } else {
            binding?.addToFavoritesCardProduct?.setImageResource(R.drawable.favorites_default)
        }

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
            binding?.salePriceProductCardProduct?.text = it.last().salePrice.toString() + getString(R.string.currency)
            if (it.last().salePrice > 0) {
                activePrice = it.last().salePrice
            } else {
                activePrice = it.last().price
            }

            mainImage = it.last().mainImage

            binding?.deliveryTime?.text = getString(R.string.deliveryTime) + it.last().deliveryTime
        })
    }

    private fun loadCategories() {
        categoriesProductsViewModel.loadProductsCategory(idProduct!!)
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
            R.id.actionBasketCardProduct -> {
                if (countToBasket > 0) {
                    basketViewModel.deleteById(idProduct!!)
                    conditionAddToBasket()

                } else {
                    binding?.nameProductCardProduct?.text?.toString()
                        ?.let { it1 ->
                            activePrice?.let { it2 ->
                                basketViewModel.startInsert(
                                    it1,
                                    it2, 1, idProduct!!
                                )
                            }
                        }
                    conditionAddToBasket()
                }
            }

            R.id.addToFavoritesCardProduct -> {
                if (countToFavorites > 0) {
                    favoriteViewModel.deleteById(idProduct ?: 0)
                } else {
                    favoriteViewModel.startInsert(
                        binding?.nameProductCardProduct?.text.toString(),
                        arguments?.getString("mainImageProduct").toString(),
                        arguments?.getInt("priceProduct") ?: return,
                        arguments?.getInt("salePriceProduct") ?: return,
                        idProduct!!
                    )
                }
            }

            R.id.addToCompareCardProduct -> {
                if (countToCompare > 0) {
                    compareViewModel.deleteById(idProduct ?: 0)
                } else {
                    compareViewModel.startInsert(
                        binding?.nameProductCardProduct?.text.toString(),
                        arguments?.getString("mainImageProduct").toString(),
                        priceProduct,
                        salePriceProduct,
                        binding?.descriptionProductCardProduct?.text.toString(),
                        binding?.deliveryTime?.text.toString(),
                        idProduct!!
                    )
                }
            }
        }
    }

    private fun conditionAddToBasket() {
        idProduct?.let {
            basketViewModel.loadProductToBasket(it.toInt()).observe(viewLifecycleOwner, Observer {
                // binding?.nameProductCardProduct?.text = it.count().toString()
                if (it.count()>0) {
                    countToBasket = it.count()
                    binding?.actionBasketCardProduct?.setImageResource(R.drawable.remove_basket)
                } else {
                    countToBasket = 0
                    binding?.actionBasketCardProduct?.setImageResource(R.drawable.basket)
                }
            })
        }
    }

    private fun conditionAddToFavorites() {
        idProduct?.let {
            favoriteViewModel.loadProductFromFavorites(it).observe(viewLifecycleOwner, Observer {
                //binding?.nameProductCardProduct?.text = it.count().toString()
                if (it.count() > 0) {
                    countToFavorites = it.count()
                    binding?.addToFavoritesCardProduct?.setImageResource(R.drawable.favorites)
                } else {
                    countToFavorites = 0
                    binding?.addToFavoritesCardProduct?.setImageResource(R.drawable.favorites_default)
                }
            })
        }
    }

    private fun conditionAddToCompare() {
        idProduct?.let {
            compareViewModel.loadProductFromCompare(it).observe(viewLifecycleOwner, Observer {
                if (it.count() > 0) {
                    countToCompare = it.count()
                    // binding?.nameProductCardProduct?.text=countToCompare.toString()
                } else {
                    countToCompare = 0
                }
            })
        }
    }


}