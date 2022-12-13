package com.example.oefstarter.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.oefstarter.databinding.ItemShopBinding
import com.example.oefstarter.models.ShopItem

class ShoppingListAdapter (private val onLongClickListener: ShoppingListOnLongClickListener, private val onCheckedChangedListener: ShoppingListOnCheckedChanged) : ListAdapter<ShopItem, ShoppingListAdapter.ShoppingListViewHolder>(ShopItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        return ShoppingListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        holder.bind(getItem(position)!!, onLongClickListener, onCheckedChangedListener)
    }

    class ShoppingListViewHolder private constructor(val binding: ItemShopBinding) : ViewHolder(binding.root) {

        fun bind(
            shopItem: ShopItem,
            onLongClickListener: ShoppingListOnLongClickListener,
            onCheckedChangedListener: ShoppingListOnCheckedChanged
        ) {
            binding.shopItem = shopItem
            binding.onLongClickListener = onLongClickListener
            binding.onCheckedChangedListener = onCheckedChangedListener
        }

        companion object {
            fun from(parent: ViewGroup): ShoppingListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemShopBinding.inflate(layoutInflater, parent, false)
                return ShoppingListViewHolder(binding)
            }
        }
    }
}

class ShoppingListOnLongClickListener(private val longClickListener: (shopItem: ShopItem) -> Boolean) {
    fun onLongClick(shopItem: ShopItem): Boolean = longClickListener(shopItem)
}

class ShoppingListOnCheckedChanged(private val onCheckedChangedListener: (shopItem: ShopItem, isChecked: Boolean) -> Unit) {
    fun onCheckChanged(shopItem: ShopItem, isChecked: Boolean) = onCheckedChangedListener(shopItem, isChecked)
}

class ShopItemDiffCallback : DiffUtil.ItemCallback<ShopItem>() {

    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.item == newItem.item && oldItem.shop == newItem.shop && oldItem.isDone == newItem.isDone
    }
}