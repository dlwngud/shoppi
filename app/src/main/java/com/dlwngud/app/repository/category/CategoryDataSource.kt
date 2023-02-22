package com.dlwngud.app.repository.category

import com.dlwngud.app.model.Category

interface CategoryDataSource {
    suspend fun getCategories(): List<Category>
}