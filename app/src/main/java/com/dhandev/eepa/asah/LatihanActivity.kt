package com.dhandev.eepa.asah

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityLatihanBinding

class LatihanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLatihanBinding
    private lateinit var sharedPred : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLatihanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPred = this.getSharedPreferences("User", MODE_PRIVATE)

        val latest = sharedPred.getInt("latestScore", 0)
        binding.apply {
            arrowBack.setOnClickListener {
                onBackPressed()
            }
            lastScore.text = "Skor terakhir : $latest"
            btnMulai.setOnClickListener {
                startActivity(Intent(this@LatihanActivity, MulaiLatihanActivity::class.java))
            }
        }
    }
}