package com.dlwngud.app.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dlwngud.app.model.CartItem
import com.dlwngud.app.repository.cart.CartRepository
import kotlinx.coroutines.launch

class CartViewModel(private val cateRepository: CartRepository): ViewModel() {

    private val _item = MutableLiveData<List<CartItem>>()
    val items: LiveData<List<CartItem>> = _item

    init {
        loadCartItem()
    }

    private fun loadCartItem(){
        viewModelScope.launch{
            _item.value = cateRepository.getCartItems()
        }
    }
}