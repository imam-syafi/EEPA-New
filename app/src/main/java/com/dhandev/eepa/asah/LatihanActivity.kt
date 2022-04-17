package com.dhandev.eepa.asah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityLatihanBinding

class LatihanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLatihanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLatihanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            arrowBack.setOnClickListener {
                onBackPressed()
            }
            btnMulai.setOnClickListener {
                startActivity(Intent(this@LatihanActivity, MulaiLatihanActivity::class.java))
            }
        }
    }
}