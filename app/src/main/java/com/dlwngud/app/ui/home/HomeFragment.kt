package com.dlwngud.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.dlwngud.app.R
import com.dlwngud.app.databinding.FragmentHomeBinding
import com.dlwngud.app.ui.common.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels { ViewModelFactory(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        setToolbar()
        setTopBanners()

//        findNavController().navigate(R.id.action_home_to_product_detail)
    }

    private fun setTopBanners() {
        with(binding.vpHomeBanner) {
            adapter = HomeBannerAdapter().apply {
                viewModel.topBanner.observe(viewLifecycleOwner) { banners ->
                    // top_banner 표시
                    submitList(banners)
                }
            }

            val pageWidth = resources.getDimension(R.dimen.viewpager_item_width)
            val pageMargin = resources.getDimension(R.dimen.viewpager_item_margin)
            val screenWidth = resources.displayMetrics.widthPixels
            val offset = screenWidth - pageWidth - pageMargin

            offscreenPageLimit = 3
            setPageTransformer { page, position ->
                page.translationX = position * -offset
            }
            TabLayoutMediator(binding.vpHomeBannerIndicator, this) { tab, position ->

            }.attach()
        }
    }

    private fun setToolbar() {
        viewModel.title.observe(viewLifecycleOwner) { title ->
            // toolbar 표시
            binding.toolbarHomeTitle.text = title.text
            Glide.with(this).load(title.iconUrl).into(binding.toolbarHomeIcon)
        }
    }
}