package com.dlwngud.app.repository.home

import com.dlwngud.app.model.HomeData

interface HomeDataSource {
    fun getHomeData(): HomeData?
}