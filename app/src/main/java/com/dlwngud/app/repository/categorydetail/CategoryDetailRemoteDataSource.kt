package com.dlwngud.app.repository.categorydetail

import com.dlwngud.app.model.CategoryDetail
import com.dlwngud.app.network.ApiClient

class CategoryDetailRemoteDataSource(private val api: ApiClient): CategoryDetailDataSource {
    override suspend fun getCategoryDetail(): CategoryDetail {
        return api.getCategoryDetail()
    }
}