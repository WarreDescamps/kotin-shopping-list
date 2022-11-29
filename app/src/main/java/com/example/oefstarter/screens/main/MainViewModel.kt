package com.example.oefstarter.screens.main

import androidx.lifecycle.ViewModel
import com.example.oefstarter.models.ShopItem
import com.example.oefstarter.screens.ShoppingListAdapter
import com.example.oefstarter.utils.ShoppingListData

class MainViewModel : ViewModel() {
    private lateinit var shoppingList : MutableList<ShopItem>

    private lateinit var _adapter : ShoppingListAdapter
    val adapter : ShoppingListAdapter
        get() = _adapter

    init {
        initAdapter()
    }

    private fun initAdapter() {
        shoppingList = ShoppingListData.getShoppingList(1).toMutableList()
        _adapter = ShoppingListAdapter()
        _adapter.feedData(shoppingList)
    }

    fun addItem(item : String, shop : String) {
        shoppingList.add(ShopItem(item, shop, false))
        adapter.notifyItemInserted(shoppingList.size - 1)
    }

    fun removeItem(index : Int) {
        shoppingList.removeAt(index)
        adapter.notifyItemRemoved(index)
    }
}