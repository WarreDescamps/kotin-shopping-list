package com.example.oefstarter.screens.main

import ShoppingListApplication
import android.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.oefstarter.models.ShopItem
import com.example.oefstarter.screens.ShoppingListAdapter
import com.example.oefstarter.screens.ShoppingListOnCheckedChanged
import com.example.oefstarter.screens.ShoppingListOnLongClickListener
import com.example.oefstarter.utils.ShoppingListData

class MainViewModel : ViewModel() {
    private var _shoppingList = ShoppingListData.getShoppingList(1)

    private var _adapter = ShoppingListAdapter(ShoppingListOnLongClickListener {
                                onLongClick(it)
                                },
                                ShoppingListOnCheckedChanged {
                                    onIsCheckedChanged(it)
                                })
    val adapter : ShoppingListAdapter
        get() = _adapter

    val shoppingList: LiveData<List<ShopItem>>
    get() {
        return _shoppingList
    }

    private fun onLongClick(shopItem: ShopItem) : Boolean {
        val builder = AlertDialog.Builder(ShoppingListApplication.appContext)
        builder.setMessage("This will delete '${shopItem.item} in ${shopItem.shop}' from your list!\nAre you sure?")
            .setPositiveButton("Yes") { dialog, _ ->
                shoppingList.value?.toMutableList().apply {
                    this?.remove(shopItem)
                }
                val index = shoppingList.value?.indexOf(shopItem)
                if (index != null)
                    adapter.notifyItemRemoved(index)
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }
        builder.create()
        builder.show()
        return true
    }

    private fun onIsCheckedChanged(shopItem: ShopItem) {
        val index = shoppingList.value?.indexOf(shopItem) ?: 0
        _adapter.notifyItemChanged(index)
    }

    fun addItem(item : String, shop : String) {
        shoppingList.value?.toMutableList().apply {
            this?.add(ShopItem(item, shop, false))
        }
        val size = shoppingList.value?.size?.minus(1)
        if (size != null) {
            adapter.notifyItemInserted(size)
        }
    }
}