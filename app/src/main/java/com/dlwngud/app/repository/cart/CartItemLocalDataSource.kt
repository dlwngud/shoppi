package com.dlwngud.app.repository.cart

import com.dlwngud.app.database.CartItemDao
import com.dlwngud.app.model.CartItem

class CartItemLocalDataSource(
    private val dao: CartItemDao
): CartItemDataSource {

    override suspend fun getCartItems(): List<CartItem> {
        return dao.load()
    }

    override suspend fun addCartItem(cartItem: CartItem) {
        dao.insert(cartItem)
    }
}