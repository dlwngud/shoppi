package com.dlwngud.app.repository.productdetail

import com.dlwngud.app.model.Product

interface ProductDetailDataSource {
    suspend fun getProductDetail(productId: String): Product
}