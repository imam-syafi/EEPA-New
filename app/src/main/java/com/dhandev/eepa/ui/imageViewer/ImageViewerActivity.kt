package com.dhandev.eepa.ui.imageViewer

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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