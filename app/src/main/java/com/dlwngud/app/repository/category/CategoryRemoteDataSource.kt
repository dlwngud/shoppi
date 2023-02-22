package com.dlwngud.app.repository.category

import com.dlwngud.app.model.Category
import com.dlwngud.app.network.ApiClient

class CategoryRemoteDataSource(private val apiClient: ApiClient): CategoryDataSource {
    override suspend fun getCategories(): List<Category> {
        return apiClient.getCategories()
    }

}