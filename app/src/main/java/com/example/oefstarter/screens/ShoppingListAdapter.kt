package com.example.oefstarter.screens

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.oefstarter.R
import com.example.oefstarter.models.ShopItem
import com.google.android.material.divider.MaterialDivider

class ShoppingListAdapter (private val onLongClickListener: ShoppingListOnLongClickListener, private val onIsCheckedChanged: ShoppingListOnIsCheckedChanged) : ListAdapter<ShopItem, ShoppingListAdapter.ShoppingListViewHolder>(ShopItemDiffCallback()) {
    inner class ShoppingListViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView)
    private lateinit var shoppingList : MutableList<ShopItem>

    override fun submitList(list: MutableList<ShopItem>?) {
        if (list != null)
            shoppingList = list
        super.submitList(list)
    }

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
            cbDone.isChecked = shoppingList[position].isDone
            onIsCheckedChanged.onIsCheckChanged(shoppingList, shoppingList[position].isDone, findViewById(R.id.mdCrossOutLine), position)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                onIsCheckedChanged.onIsCheckChanged(
                    shoppingList,
                    isChecked,
                    findViewById(R.id.mdCrossOutLine),
                    holder.layoutPosition)
            }
            setOnLongClickListener { onLongClickListener.onLongClick(this.context, holder.layoutPosition) }
        }
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }
}

class ShoppingListOnLongClickListener(private val longClickListener: (context: Context, position: Int) -> Boolean) {
    fun onLongClick(context: Context, position: Int): Boolean = longClickListener(context, position)
}

class ShoppingListOnIsCheckedChanged(private val isCheckedChanged: (shoppingList: List<ShopItem>, isChecked : Boolean, materialDivider: MaterialDivider, position: Int) -> Unit) {
    fun onIsCheckChanged(shoppingList: List<ShopItem>, isChecked : Boolean, materialDivider: MaterialDivider, position: Int) = isCheckedChanged(shoppingList, isChecked, materialDivider, position)
}

class ShopItemDiffCallback : DiffUtil.ItemCallback<ShopItem>() {

    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.item == newItem.item && oldItem.shop == newItem.shop && oldItem.isDone == newItem.isDone
    }
}