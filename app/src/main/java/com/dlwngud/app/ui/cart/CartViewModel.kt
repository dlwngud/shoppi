package com.dlwngud.app.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dlwngud.app.model.CartItem

class CartViewModel: ViewModel() {

    private val _item = MutableLiveData<List<CartItem>>()
    val items: LiveData<List<CartItem>> = _item

    fun loadCartItem(){
        _item.value
    }
}