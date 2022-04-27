package com.example.tensorhw8.adapter

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.tensorhw8.model.ListItem

class ItemsDiffUtilCallback(
    private val oldList: List<ListItem>,
    private val newList: List<ListItem>
) : DiffUtil.Callback() {
    
    companion object {
        val TAG: String = ItemsDiffUtilCallback::class.java.simpleName
    }
    
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        Log.d(TAG, "areItemsTheSame called: oldItem=${oldItem.id}, newItem=${newItem.id} -> ${oldItem.id == newItem.id}")
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[oldItemPosition]

        Log.d(TAG, "getChangePayload called: oldItem=$oldItem, newItem=$newItem")

        val diff = Bundle()
        Log.d(TAG, "getChangePayload called: oldItem.name=${oldItem.name}, newItem.name=${newItem.name}")
        if (oldItem.name != newItem.name) {
            diff.putString(KEY_NAME, newItem.name)
        }

        Log.d(TAG, "diff=$diff")

        return if (diff.size() == 0) {
            super.getChangePayload(oldItemPosition, newItemPosition)
        } else diff
    }
}