package com.example.memeshare

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import coil.load

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("memeApiStatus")
fun bindStatus(imgView: ImageView, status: MemeApiStatus) {
    when (status) {
        MemeApiStatus.ERROR ->{
            imgView.visibility = View.VISIBLE
            imgView.setImageResource(R.drawable.ic_connection_error)
        }else -> {
            imgView.visibility = View.GONE
        }
    }
}
@BindingAdapter("removeimage")
fun bindremove(imgView: ImageView, status: MemeApiStatus) {
    if (status == MemeApiStatus.ERROR) {
        imgView.visibility = View.GONE
    }
    }
class BindingAdapters {
}