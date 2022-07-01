package com.dhandev.eepa.ui.imageViewer

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dhandev.eepa.databinding.ActivityImageViewerMateriBinding
import com.dhandev.eepa.helper.glideImage

class ImageViewerMateriActivity : AppCompatActivity() {
    private lateinit var binding : ActivityImageViewerMateriBinding
    private lateinit var sharedPred : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageViewerMateriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPred = this.getSharedPreferences("Tampilan", MODE_PRIVATE)

        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }
        var URL = sharedPred.getString("url", null)
        var desc = sharedPred.getString("desc", null)

        glideImage.load(this, URL, binding.gambar)

        binding.desc.text = desc

    }
}