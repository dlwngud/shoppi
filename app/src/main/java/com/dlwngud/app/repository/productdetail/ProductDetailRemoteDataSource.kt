package com.dlwngud.app.repository.productdetail

import com.dlwngud.app.model.Product
import com.dlwngud.app.network.ApiClient

class ProductDetailRemoteDataSource(
    private val api: ApiClient
) : ProductDetailDataSource {

    override suspend fun getProductDetail(productId: String): Product {
        return api.getProductDetail(productId)
    }
}