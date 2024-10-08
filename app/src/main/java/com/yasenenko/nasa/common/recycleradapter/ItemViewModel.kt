package com.yasenenko.nasa.common.recycleradapter

import android.view.View

abstract class ItemViewModel<Item> {

    abstract val item: Item

    private var itemClickHandler: ((view: View, item: Item) -> Unit)? = null
    private var itemLongClickHandler: ((view: View, item: Item) -> Unit)? = null

    fun setOnItemClickListener(itemClickHandler: (view: View, item: Item) -> Unit) = this.apply {
        this.itemClickHandler = itemClickHandler
    }

    fun onItemClick(view: View) {
        itemClickHandler?.invoke(view, item)
    }

    fun setOnItemLongClickListener(itemLongClickHandler: (view: View, item: Item) -> Unit) =
        this.apply {
            this.itemLongClickHandler = itemLongClickHandler
        }

    fun onItemLongClick(view: View): Boolean {
        itemLongClickHandler?.invoke(view, item)
        return true
    }
}