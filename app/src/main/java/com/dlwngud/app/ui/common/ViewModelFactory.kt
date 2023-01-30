package com.dlwngud.app.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dlwngud.app.AssetLoader
import com.dlwngud.app.repository.HomeAssetDataSource
import com.dlwngud.app.repository.HomeRepository
import com.dlwngud.app.ui.home.HomeViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val repository = HomeRepository(HomeAssetDataSource(AssetLoader(context)))
            return HomeViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}