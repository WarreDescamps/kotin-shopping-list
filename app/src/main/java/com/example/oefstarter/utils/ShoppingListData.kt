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
            val users = UserData.getUsers().value?.keys ?: return MutableLiveData<MutableList<ShopItem>>()
            val shoppingList = mutableListOf<ShopItem>()
            for (i in 0..20) {
                shoppingList.add(
                    ShopItem(
                        users.elementAt(Random.nextInt(1, users.size + 1)),
                        allGroceries[i % allGroceries.size],
                        allShops[Random.nextInt(allShops.size)], Random.nextBoolean()
                    )
                )
            }
            val list = MutableLiveData<MutableList<ShopItem>>()
            list.value = shoppingList
            return list
        }

        fun getShoppingList(id: Long): LiveData<List<ShopItem>> {
            val userShoppingList = mutableListOf<ShopItem>()
            for (shopItem in list.value ?: mutableListOf()) {
                userShoppingList.add(shopItem)
            }
            val list = MutableLiveData<List<ShopItem>>()
            list.value = userShoppingList
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