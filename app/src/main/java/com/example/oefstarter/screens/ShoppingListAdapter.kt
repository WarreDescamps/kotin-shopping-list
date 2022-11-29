package com.example.oefstarter.screens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oefstarter.R
import com.example.oefstarter.models.ShopItem
import com.google.android.material.divider.MaterialDivider

class ShoppingListAdapter (var shoppingList : MutableList<ShopItem>) : RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>() {
    inner class ShoppingListViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shop, parent, false)
        return ShoppingListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        holder.itemView.apply {
            var tvItem = findViewById<TextView>(R.id.tvItem)
            tvItem.text = shoppingList[position].item
            var tvShop = findViewById<TextView>(R.id.tvShop)
            tvShop.text = shoppingList[position].shop
            var cbDone = findViewById<CheckBox>(R.id.cbDone)
            cbDone.setOnCheckedChangeListener { _, isChecked -> onIsCheckedChanged(isChecked, findViewById(
                R.id.mdCrossOutLine
            ), holder.layoutPosition) }
            cbDone.isChecked = shoppingList[position].isDone
            var ibCross = findViewById<ImageButton>(R.id.ibCross)
            ibCross.setOnClickListener { remove(holder.layoutPosition) }
        }
    }

    private fun onIsCheckedChanged(isChecked : Boolean, materialDivider: MaterialDivider, position: Int) {
        shoppingList[position].isDone = isChecked
        if (isChecked) {
            materialDivider.visibility = View.VISIBLE
        }
        else {
            materialDivider.visibility = View.GONE
        }
    }

    private fun remove(position : Int) {
        shoppingList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }
}