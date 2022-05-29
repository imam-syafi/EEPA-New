package com.dhandev.eepa.ui.imageViewer

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.RoundedCorner
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.Target
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityImageViewerBinding
import com.dhandev.eepa.helper.glideImage

class ImageViewerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageViewerBinding
    private lateinit var sharedPredGaleri : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPredGaleri = this.getSharedPreferences("User", MODE_PRIVATE)

        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }

        var URLGaleri = sharedPredGaleri.getString("urlHead", null)
        var descGaleri = sharedPredGaleri.getString("desc", null)

        glideImage.load(this, URLGaleri, binding.gambar)

        binding.desc.text = descGaleri

    }
}