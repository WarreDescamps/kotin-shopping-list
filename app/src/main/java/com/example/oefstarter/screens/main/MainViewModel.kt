package com.example.oefstarter.screens.main

import androidx.lifecycle.ViewModel
import com.example.oefstarter.allGroceries
import com.example.oefstarter.allShops
import com.example.oefstarter.models.ShopItem
import com.example.oefstarter.screens.ShoppingListAdapter
import com.example.oefstarter.utils.ShoppingListData
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private lateinit var shoppingList : MutableList<ShopItem>

    private lateinit var _adapter : ShoppingListAdapter
    val adapter : ShoppingListAdapter
        get() = _adapter

    init {
        initAdapter()
    }

    private fun initAdapter() {
        shoppingList = mutableListOf<ShopItem>()
        for (i in 0..30) {
            shoppingList.add(ShopItem(allGroceries[i % allGroceries.size], allShops[Random.nextInt(allShops.size)], Random.nextBoolean()))
        }
        _adapter = ShoppingListAdapter(shoppingList)
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