package com.example.oefstarter.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.oefstarter.models.allGroceries
import com.example.oefstarter.models.allShops
import com.example.oefstarter.models.ShopItem
import kotlin.random.Random

class ShoppingListData {
    companion object {
        private val list = seed()

        private fun seed() : List<ShopItem> {
            val shoppingList = mutableListOf<ShopItem>()
            for (i in 0..30) {
                shoppingList.add(
                    ShopItem(
                        allGroceries[i % allGroceries.size], allShops[Random.nextInt(
                            allShops.size)], Random.nextBoolean())
                )
            }
            return shoppingList
        }

        fun getShoppingList(id: Long): LiveData<List<ShopItem>> {
            var list = MutableLiveData<List<ShopItem>>()
            list.value = this.list
            return list
        }
    }
}