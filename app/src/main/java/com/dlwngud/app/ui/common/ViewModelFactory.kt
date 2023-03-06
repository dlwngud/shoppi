package com.dlwngud.app.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dlwngud.app.AssetLoader
import com.dlwngud.app.network.ApiClient
import com.dlwngud.app.repository.category.CategoryRemoteDataSource
import com.dlwngud.app.repository.category.CategoryRepository
import com.dlwngud.app.repository.categorydetail.CategoryDetailRemoteDataSource
import com.dlwngud.app.repository.categorydetail.CategoryDetailRepository
import com.dlwngud.app.repository.home.HomeAssetDataSource
import com.dlwngud.app.repository.home.HomeRepository
import com.dlwngud.app.ui.category.CategoryViewModel
import com.dlwngud.app.ui.categorydetail.CategoryDetailViewModel
import com.dlwngud.app.ui.home.HomeViewModel

// viewModel 생성 클래스

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                val repository = HomeRepository(HomeAssetDataSource(AssetLoader(context)))
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> {
                val repository = CategoryRepository(CategoryRemoteDataSource(ApiClient.create()))
                CategoryViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CategoryDetailViewModel::class.java) -> {
                val repository = CategoryDetailRepository(CategoryDetailRemoteDataSource(ApiClient.create()))
                CategoryDetailViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}