package com.jh.thingsflow.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("banner")
fun ImageView.setBannerUrl(url: String) {
    Glide.with(this.context)
        .load( url )
        .into(this)
}