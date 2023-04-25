package com.dlwngud.app.repository.cart

import com.dlwngud.app.model.CartItem

interface CartItemDataSource {

    // 데이터베이스에 데이터 요청하는 작업은 메인 스레스에서 동작하면 안됨
    // 그래서 suspend를 사용하여 코루틴 스코프에서 사용을 강제
    suspend fun getCartItems(): List<CartItem>

    suspend fun addCartItem(cartItem: CartItem)
}