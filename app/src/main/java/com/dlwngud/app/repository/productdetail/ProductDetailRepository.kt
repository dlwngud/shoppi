package com.dlwngud.app.repository.productdetail

import com.dlwngud.app.model.Product

class ProductDetailRepository(
    private val remoteDataSource: ProductDetailDataSource
) {
    suspend fun getProductDetail(productId: String): Product {
        return remoteDataSource.getProductDetail(productId)
    }
}