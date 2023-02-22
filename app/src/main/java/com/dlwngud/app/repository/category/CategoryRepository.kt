package com.dlwngud.app.repository.category

import com.dlwngud.app.model.Category

class CategoryRepository(
    private val remoteDataSource: CategoryDataSource
) {
    suspend fun getCategories(): List<Category> {
        return remoteDataSource.getCategories()
    }
}