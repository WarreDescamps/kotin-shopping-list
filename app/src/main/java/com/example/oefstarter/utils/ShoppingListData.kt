package com.example.oefstarter.utils

import com.example.oefstarter.models.allGroceries
import com.example.oefstarter.models.allShops
import com.example.oefstarter.models.ShopItem
import kotlin.random.Random

class ShoppingListData {
    companion object {
        fun getShoppingList(userId: Long): List<ShopItem>{
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
    }
}