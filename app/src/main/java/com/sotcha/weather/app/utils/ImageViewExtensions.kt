package com.sotcha.weather.app.utils

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


/**
 * Load an image from uri
 *
 * @param uri
 */
fun ImageView.loadImageFromUrl(uri: Uri) {
    Glide.with(this.context)
        .load(uri)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}