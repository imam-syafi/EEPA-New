package com.dhandev.eepa.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityTentangBinding

class TentangActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTentangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTentangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this, "Tekan gambar untuk melihat detail", Toast.LENGTH_LONG).show()
        binding.apply {
            arrowBack.setOnClickListener {
                onBackPressed()
            }
            Glide.with(this@TentangActivity)
                .load(R.drawable.ramdhan)
                .circleCrop()
                .into(ramdhan)
            Glide.with(this@TentangActivity)
                .load(R.drawable.r_oktova)
                .circleCrop()
                .into(pakOktova)
            ramdhan.setOnClickListener {
                if (detailRamdhan.isVisible){
                    detailRamdhan.visibility = View.GONE
                } else {
                    detailRamdhan.visibility = View.VISIBLE
                }
            }
            pakOktova.setOnClickListener {
                if (detailPakOktova.isVisible){
                    detailPakOktova.visibility = View.GONE
                } else {
                    detailPakOktova.visibility = View.VISIBLE
                }
            }
        }
    }
}