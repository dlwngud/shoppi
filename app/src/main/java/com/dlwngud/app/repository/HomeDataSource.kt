package com.dlwngud.app.repository

import com.dlwngud.app.model.HomeData

interface HomeDataSource {
    fun getHomeData(): HomeData?
}