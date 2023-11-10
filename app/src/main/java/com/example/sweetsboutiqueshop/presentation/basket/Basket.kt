package com.example.sweetsboutiqueshop.presentation.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetsboutiqueshop.data.api.ApiClient
import com.example.sweetsboutiqueshop.data.models.BasketModel
import com.example.sweetsboutiqueshop.databinding.BasketBinding
import com.example.sweetsboutiqueshop.presentation.catalog.viewModels.BasketViewModel
import okhttp3.ResponseBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Basket : Fragment() {

    private var binding: BasketBinding? = null
    private var adapter: BasketAdapter? = null
    val viewModel: BasketViewModel by viewModel()
    var products: String = ""

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

        binding?.orderBasket?.setOnClickListener(View.OnClickListener {
            viewModel.loadProductsFromBasket.observe(viewLifecycleOwner, Observer {
                products =
                    it.map { it.name + " " + it.count + " шт." + " " + it.price * it.count + " Р" }
                        .toString()
            })

            insertOrder(
                binding?.enterName?.text?.toString(),
                binding?.enterPhone?.text?.toString(),
                products
            )
            viewModel.clear()
            binding?.enterName?.setText("")
            binding?.enterPhone?.setText("")
        })

        return binding?.root
    }


    private fun initRecyclerProductsBasket() {
        binding?.recyclerProductsBasket?.layoutManager = LinearLayoutManager(context)
        adapter = BasketAdapter({ model: BasketModel,
                                  tag: String ->
            editCount(model, tag)
        },
            { model: BasketModel ->
                deleteProduct(model)
            })
        binding?.recyclerProductsBasket?.adapter = adapter

    }

    private fun loadProductsBasket(){
        viewModel.loadProductsFromBasket.observe(viewLifecycleOwner, Observer {
            adapter?.setList(it)
            adapter?.notifyDataSetChanged()

            if (it.isNotEmpty()) {
                binding?.sectionEnterName?.visibility = View.VISIBLE
                binding?.sectionEnterTel?.visibility = View.VISIBLE
                binding?.clearBasket?.visibility = View.VISIBLE
                binding?.orderBasket?.visibility = View.VISIBLE
                binding?.recyclerProductsBasket?.visibility = View.VISIBLE
                binding?.messageEmptyBasket?.visibility = View.GONE
            } else {
                binding?.sectionEnterName?.visibility = View.GONE
                binding?.sectionEnterTel?.visibility = View.GONE
                binding?.clearBasket?.visibility = View.GONE
                binding?.orderBasket?.visibility = View.GONE
                binding?.recyclerProductsBasket?.visibility = View.GONE
                binding?.messageEmptyBasket?.visibility = View.VISIBLE
            }
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

    private fun insertOrder(name: String?, phone: String?, descOrder: String?) {
        val callInsertOrder: Call<ResponseBody?>? =
            ApiClient.instance?.api?.createOrder(name, phone, descOrder)
        callInsertOrder?.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                Toast.makeText(context, "ЗАКАЗ ОТПРАВЛЕН", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(context, "ОШИБКА, ПОПРОБУЙТЕ ЕЩЕ РАЗ", Toast.LENGTH_SHORT).show()
            }
        })
    }

}