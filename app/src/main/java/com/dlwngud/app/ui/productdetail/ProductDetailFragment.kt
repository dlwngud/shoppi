package com.dlwngud.app.ui.productdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dlwngud.app.R
import com.dlwngud.app.common.KEY_PRODUCT_ID
import com.dlwngud.app.databinding.FragmentProductDetailBinding
import com.dlwngud.app.ui.common.EventObserver
import com.dlwngud.app.ui.common.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProductDetailFragment : Fragment() {
    private val viewModel: ProductDetailViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var binding: FragmentProductDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        setNavigation()
        requireArguments().getString(KEY_PRODUCT_ID)?.let { productId ->
            setLayout(productId)
        }
//        setAddCart()
    }

//    private fun setAddCart() {
//        viewModel.addCartEvent.observe(viewLifecycleOwner, EventObserver {
//            MaterialAlertDialogBuilder(requireContext())
//                .setTitle(getString(R.string.dialog_title_add_cart))
//                .setPositiveButton(getString(R.string.dialog_button_label_confirm)) { dialog, which ->
//
//                }
//                .show()
//        })
//    }

    private fun setNavigation() {
        binding.toolbarProductDetail.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setLayout(productId: String) {
        viewModel.loadProductDetail(productId)
        val descriptionAdapter = ProductDescriptionAdapter()
        binding.rvProductDetail.adapter = descriptionAdapter
        viewModel.product.observe(viewLifecycleOwner) { product ->
            binding.product = product
            descriptionAdapter.submitList(product.descriptions)
        }
    }
}