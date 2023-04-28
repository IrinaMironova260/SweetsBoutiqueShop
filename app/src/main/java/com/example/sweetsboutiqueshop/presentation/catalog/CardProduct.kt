package com.example.sweetsboutiqueshop.presentation.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sweetsboutiqueshop.R
import com.example.sweetsboutiqueshop.databinding.CardProductBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class CardProduct : BottomSheetDialogFragment() {

    private var binding: CardProductBinding? = null
    private var idProduct: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CardProductBinding.inflate(layoutInflater, container,false)

        idProduct = arguments?.getInt("idProduct")

        return binding?.root
    }


}