package com.example.oefstarter.screens.main

import android.app.AlertDialog
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.oefstarter.models.ShopItem
import com.example.oefstarter.screens.ShoppingListAdapter
import com.example.oefstarter.screens.ShoppingListOnCheckedChanged
import com.example.oefstarter.screens.ShoppingListOnLongClickListener
import com.example.oefstarter.screens.UserSingelton
import com.example.oefstarter.utils.ShoppingListData
import com.example.oefstarter.utils.UserData

class MainViewModel : ViewModel() {
    private var _adapter = ShoppingListAdapter(ShoppingListOnLongClickListener { shopItem ->
                                onLongClick(shopItem)
                                },
                                ShoppingListOnCheckedChanged { shopItem, isChecked ->
                                    onIsCheckedChanged(shopItem, isChecked)
                                })
    val adapter : ShoppingListAdapter
        get() = _adapter

    private lateinit var _shoppingList: LiveData<MutableList<ShopItem>>
    val shoppingList: LiveData<MutableList<ShopItem>>
    get() {
        return _shoppingList
    }

    private lateinit var _alertBuilder: AlertDialog.Builder
    var alertBuilder: AlertDialog.Builder
    get() {
        return _alertBuilder
    }
    set(value) {
        _alertBuilder = value
    }

    init {
        val user = UserSingelton.instance().user
        if (user != null) ShoppingListData.getShoppingList(user.id)
    }

    private fun onLongClick(shopItem: ShopItem) : Boolean {
        _alertBuilder.setMessage("This will delete '${shopItem.item} in ${shopItem.shop}' from your list!\nAre you sure?")
            .setPositiveButton("Yes") { dialog, _ ->
                val index = shoppingList.value?.indexOf(shopItem)
                ShoppingListData.removeShoppingListItem(shopItem)
                if (index != null)
                    adapter.notifyItemRemoved(index)
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }
        _alertBuilder.create()
        _alertBuilder.show()
        return true
    }

    private fun onIsCheckedChanged(shopItem: ShopItem, isChecked: Boolean) {
        if (shopItem.isDone == isChecked)
            return
        shopItem.isDone = isChecked
        val index = shoppingList.value?.indexOf(shopItem) ?: 0
        _adapter.notifyItemChanged(index)
    }

    fun addItem(item : String, shop : String) {
        val user = UserSingelton.instance().user ?: return
        ShoppingListData.addShoppingListItem(ShopItem(user.id, item, shop, false))
        val size = shoppingList.value?.size?.minus(1)
        if (size != null) {
            adapter.notifyItemInserted(size)
        }
    }
}