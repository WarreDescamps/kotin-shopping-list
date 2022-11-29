package com.example.oefstarter.screens.main

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.oefstarter.models.ShopItem
import com.example.oefstarter.screens.ShoppingListAdapter
import com.example.oefstarter.screens.ShoppingListOnIsCheckedChanged
import com.example.oefstarter.screens.ShoppingListOnLongClickListener
import com.example.oefstarter.utils.ShoppingListData
import com.google.android.material.divider.MaterialDivider

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
        _adapter = ShoppingListAdapter(
            ShoppingListOnLongClickListener { context, position ->
                onLongClick(context, position)
            },
            ShoppingListOnIsCheckedChanged { shoppingList, isChecked, materialDivider, position ->
                onIsCheckedChanged(shoppingList, isChecked, materialDivider, position)
            }
        )
        _adapter.feedData(shoppingList)
    }

    private fun onIsCheckedChanged(shoppingList: List<ShopItem>, isChecked : Boolean, materialDivider: MaterialDivider, position: Int) {
        shoppingList[position].isDone = isChecked
        if (isChecked) {
            materialDivider.visibility = View.VISIBLE
        }
        else {
            materialDivider.visibility = View.GONE
        }
    }

    private fun onLongClick(context: Context, position: Int) : Boolean {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("This will delete ${"test"} from your list!\nAre you sure?")
            .setPositiveButton("Yes") { dialog, _ ->
                removeItem(position)
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }
        builder.create()
        builder.show()
        return true
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