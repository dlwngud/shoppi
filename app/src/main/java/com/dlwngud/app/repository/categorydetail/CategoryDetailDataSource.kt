package com.dlwngud.app.repository.categorydetail

import com.dlwngud.app.model.CategoryDetail

interface CategoryDetailDataSource {

    suspend fun getCategoryDetail(): CategoryDetail
}