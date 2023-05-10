package com.example.sweetsboutiqueshop.presentation.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetsboutiqueshop.data.models.BasketModel
import com.example.sweetsboutiqueshop.databinding.BasketBinding
import com.example.sweetsboutiqueshop.presentation.catalog.viewModels.BasketViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class Basket : Fragment() {

    private var binding: BasketBinding? = null
    private var adapter: BasketAdapter? = null
    val viewModel: BasketViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = BasketBinding.inflate(layoutInflater, container, false)

        initRecyclerProductsBasket()
        loadProductsBasket()

        binding?.clearBasket?.setOnClickListener(View.OnClickListener {
            viewModel.clear()
        })
        return binding?.root
    }


    private fun initRecyclerProductsBasket() {
        binding?.recyclerProductsBasket?.layoutManager = LinearLayoutManager(context)
        adapter = BasketAdapter({
                model: BasketModel,
                tag: String -> editCount(model, tag) },
            { model: BasketModel -> deleteProduct(model)
            })
        binding?.recyclerProductsBasket?.adapter = adapter

    }

    private fun loadProductsBasket() {
        viewModel.loadProductsFromBasket.observe(viewLifecycleOwner, Observer {
            adapter?.setList(it)
            adapter?.notifyDataSetChanged()
        })
    }


    private fun editCount(model: BasketModel, tag: String) {
        var count = model.count
        if (tag == "plus") {
            count++
        } else {
            if (count > 1) {
                count--
            }
        }
        viewModel.startUpdateProduct(model.id, model.name, model.price, count, model.productId)
    }

    private fun deleteProduct(model: BasketModel) {
        viewModel.delete(model)
    }

}