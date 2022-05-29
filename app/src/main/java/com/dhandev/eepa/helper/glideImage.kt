package com.dhandev.eepa.helper

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.dhandev.eepa.R

object glideImage {
    fun load (context: Context, url : String?, imageView: ImageView){
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.memuatgambar)
            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            .into(imageView)
    }
}