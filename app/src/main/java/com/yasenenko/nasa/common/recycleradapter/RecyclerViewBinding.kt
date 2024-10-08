package com.yasenenko.nasa.common.recycleradapter

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.yasenenko.nasa.R


@BindingAdapter(
    "items",
)
fun RecyclerView.setRecyclerViewItems(
    items: List<RecyclerItem>?
) {
    var adapter = (this.adapter as? DataBindingRecyclerAdapter)
    if (adapter == null) {
        adapter = DataBindingRecyclerAdapter()
        this.adapter = adapter
    }

    adapter.submitList(items.orEmpty())
}


@BindingAdapter(
    "setVisibility"
)
fun View.setVisibility(
    bool: Boolean?,
) {
    if (bool == true) {
        this.isVisible = bool
    } else {
        this.isVisible = false
    }
}


@BindingAdapter(
    "loadImage",
)
fun ImageView.loadImage(
    url: String?,
) {
    if (url != null) {
        layout(0, 0, 0, 0)
        Glide.with(this.context)
            .load(url.normalize())
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}

private fun String.normalize(): String {
    return this.replace("http:", "https:")
}



