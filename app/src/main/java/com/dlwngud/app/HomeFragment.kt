package com.dlwngud.app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dlwngud.app.databinding.FragmentHomeBinding
import com.google.gson.Gson
import org.json.JSONObject

class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding

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

            // toolbar 표시
            binding.toolbarHomeTitle.text = homeData.title.text
            Glide.with(this).load(homeData.title.iconUrl).into(binding.toolbarHomeIcon)

            // top_banner 표시
            binding.vpHomeBanner.adapter = HomeBannerAdapter().apply {
                submitList(homeData.topBanner)
            }
        }
    }
}