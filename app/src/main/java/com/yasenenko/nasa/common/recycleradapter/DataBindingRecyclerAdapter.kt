package com.yasenenko.nasa.common.recycleradapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.yasenenko.nasa.common.bind

class DataBindingRecyclerAdapter : ListAdapter<RecyclerItem, BindingViewHolder>(DiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return getItem(position).layoutId
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, viewType, parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BindingViewHolder,
        position: Int
    ) {
        holder.run {
            getItem(position).bind(binding)
            if (binding.hasPendingBindings()) {
                binding.executePendingBindings()
            }
        }
    }
}
