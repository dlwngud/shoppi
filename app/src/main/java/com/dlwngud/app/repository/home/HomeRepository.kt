package com.dlwngud.app.repository.home

import com.dlwngud.app.model.HomeData

class HomeRepository(private val assetDataSource: HomeAssetDataSource) {

    fun getHomeData(): HomeData? {
        return assetDataSource.getHomeData()
    }
}