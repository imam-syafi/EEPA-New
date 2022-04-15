package com.dhandev.eepa.ui.imageViewer

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.aghajari.zoomhelper.ZoomHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityImageViewerMateriBinding

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

        Glide.with(this)
            .load(URL)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.memuatgambar)
            .centerInside()
            .into(binding.gambar)

        binding.desc.text = desc

    }
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return ZoomHelper.getInstance().dispatchTouchEvent(ev!!,this) || super.dispatchTouchEvent(ev)
    }
}