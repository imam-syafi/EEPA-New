package com.dhandev.eepa.ui.settings

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityTentangBinding
import com.dhandev.eepa.helper.customTab
import com.dhandev.eepa.helper.utils.dialog

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
            androidStudio.setOnClickListener { dialog(this@TentangActivity, R.drawable.icons8_android_studio, "Android Studio", "Merupakan perangkat lunak yang digunakan untuk mengembangkan aplikasi Android dengan fitur yang melimpah dan dukungan Google.", "https://developer.android.com/studio") }
            figma.setOnClickListener { dialog(this@TentangActivity, R.drawable.icons8_figma,"Figma", "Merupakan website yang digunakan untuk membuat prototipe aplikasi sebelum masuk ke tahap produksi menggunakan android studio", "https://www.figma.com/") }
            icons8.setOnClickListener { dialog(this@TentangActivity, R.drawable.icons8_icons8, "Icons8", "Merupakan website yang menyediakan kumpulan icon, logo, dan ilustrasi gratis yang dapat digunakan untuk berbagai kebutuhan", "https://icons8.com/") }
        }
    }
}