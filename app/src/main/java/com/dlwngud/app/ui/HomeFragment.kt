package com.dlwngud.app.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.dlwngud.app.AssetLoader
import com.dlwngud.app.HomeBannerAdapter
import com.dlwngud.app.HomeData
import com.dlwngud.app.R
import com.dlwngud.app.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        findNavController().navigate(R.id.action_home_to_product_detail)

        val assetLoader = AssetLoader()
        val homeJsonString = assetLoader.getJsonString(requireContext(), "home.json")
        Log.d("homeData", homeJsonString ?: "")

        if(!homeJsonString.isNullOrEmpty()){
            val gson = Gson()
            val homeData = gson.fromJson(homeJsonString, HomeData::class.java)

            viewModel.title.observe(viewLifecycleOwner) { title ->
                // toolbar 표시
                binding.toolbarHomeTitle.text = title.text
                Glide.with(this).load(title.iconUrl).into(binding.toolbarHomeIcon)
            }

            viewModel.topBanner.observe(viewLifecycleOwner) { banners ->
                // top_banner 표시
                binding.vpHomeBanner.adapter = HomeBannerAdapter().apply {
                    submitList(banners)
                }
            }

            val pageWidth = resources.getDimension(R.dimen.viewpager_item_width)
            val pageMargin = resources.getDimension(R.dimen.viewpager_item_margin)
            val screenWidth = resources.displayMetrics.widthPixels
            val offset = screenWidth - pageWidth - pageMargin

            binding.vpHomeBanner.offscreenPageLimit = 3
            binding.vpHomeBanner.setPageTransformer { page, position ->
                page.translationX = position * -offset
            }
            TabLayoutMediator(binding.vpHomeBannerIndicator, binding.vpHomeBanner) { tab, position ->

            }.attach()
        }
    }
}