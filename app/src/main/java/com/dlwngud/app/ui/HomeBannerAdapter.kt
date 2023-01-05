package com.dlwngud.app

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dlwngud.app.databinding.FragmentHomeBinding
import com.dlwngud.app.databinding.ItemHomeBannerBinding
import java.text.DecimalFormat
import kotlin.math.roundToInt

class HomeBannerAdapter :
    ListAdapter<Banner, HomeBannerAdapter.HomeBannerViewHolder>(BannerDiffCallback()) {

    // 뷰홀더를 생성(레이아웃 생성)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerViewHolder {
        val binding =
            ItemHomeBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeBannerViewHolder(binding)
    }

    // 뷰홀더가 재활용될 때 실행되는 메서드
    override fun onBindViewHolder(holder: HomeBannerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HomeBannerViewHolder(private val binding: ItemHomeBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(banner: Banner) {
            loadImage(banner.backgroundImageUrl, binding.ivBannerImage)

            binding.tvBannerBadge.text = banner.badge.label
            binding.tvBannerBadge.background =
                ColorDrawable(Color.parseColor(banner.badge.backgroundColor))
            binding.tvBannerTitle.text = banner.label

            loadImage(banner.productDetail.thumbnailImageUrl, binding.ivBannerDetailThumbnail)
            binding.tvBannerDetailBrandLabel.text = banner.productDetail.brandName
            binding.tvBannerDetailProductLabel.text = banner.productDetail.label
            binding.tvBannerDetailProductDiscountRate.text = "${banner.productDetail.discountRate}%"
            calculateDiscountAmount(
                binding.tvBannerDetailProductDiscountPrice,
                banner.productDetail.discountRate,
                banner.productDetail.price
            )
            applyPriceFormat(binding.tvBannerDetailProductPrice, banner.productDetail.price)
        }

        private fun calculateDiscountAmount(view: TextView, discountRate: Int, price: Int) {
            val discountPrice = (price * (100 - discountRate) / 100.0).roundToInt()
            applyPriceFormat(view, discountPrice)
        }

        private fun applyPriceFormat(view: TextView, price: Int) {
            val decimalFormat = DecimalFormat("#,###")
            view.text = decimalFormat.format(price) + "원"
        }

        private fun loadImage(urlString: String, imageView: ImageView) {
            Glide.with(itemView).load(urlString).into(imageView)
        }
    }
}

class BannerDiffCallback : DiffUtil.ItemCallback<Banner>() {
    override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem.productDetail.productId == newItem.productDetail.productId
    }

    override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem == newItem
    }

}