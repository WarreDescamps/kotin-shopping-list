package com.example.oefstarter.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.oefstarter.models.allGroceries
import com.example.oefstarter.models.allShops
import com.example.oefstarter.models.ShopItem
import kotlin.random.Random

class ShoppingListData {
    companion object {
        private val list: MutableLiveData<MutableList<ShopItem>> = seed()

        private fun seed() : MutableLiveData<MutableList<ShopItem>> {
            val shoppingList = mutableListOf<ShopItem>()
            for (i in 0..10) {
                shoppingList.add(
                    ShopItem(
                        allGroceries[i % allGroceries.size], allShops[Random.nextInt(
                            allShops.size)], Random.nextBoolean())
                )
            }
            val list = MutableLiveData<MutableList<ShopItem>>()
            list.value = shoppingList
            return list
        }

        fun getShoppingList(id: Long): LiveData<MutableList<ShopItem>> {
            return list
        }

        fun addShoppingListItem(shopItem: ShopItem) {
            val oldValue = list.value ?: mutableListOf()
            oldValue.add(shopItem)
            list.value = oldValue
        }

        fun removeShoppingListItem(shopItem: ShopItem) {
            val oldValue = list.value ?: mutableListOf()
            oldValue.remove(shopItem)
            list.value = oldValue
        }
    }
}