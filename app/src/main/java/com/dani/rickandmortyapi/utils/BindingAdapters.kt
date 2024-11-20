package com.dani.rickandmortyapi.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.dani.rickandmortyapi.R

@Suppress("TooManyFunctions")
object BindingAdapters {
    private const val BLINKING_DURATION = 1500L


    @BindingAdapter("visible")
    @JvmStatic
    fun setVisibile(view: View, value: Boolean?) {
        view.visibility = if (value == true) View.VISIBLE else View.GONE
    }

    @BindingAdapter(
        value = ["imageUrl"],
        requireAll = false
    )
    @JvmStatic
    fun setImageUrlWithDefault(
        imageView: ImageView,
        image: String?
    ) {
        if (image==null) imageView.setImageDrawable(null)
        else {
            Glide.with(imageView)
                .load(image)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView)
        }
    }
}