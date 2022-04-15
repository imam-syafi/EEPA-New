package com.dhandev.eepa.asah

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityContohBinding

class ContohActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContohBinding
    private lateinit var sharedPred : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContohBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPred = this.getSharedPreferences("Contoh Soal", MODE_PRIVATE)

        binding.apply {
            arrowBack.setOnClickListener {
                onBackPressed()
            }
            cth1.setOnClickListener {
                val Editor:SharedPreferences.Editor = sharedPred.edit()
                Editor.putInt("nomor", 1)
                Editor.apply()
                startActivity(Intent(this@ContohActivity, NomorContohActivity::class.java))
            }
            cth2.setOnClickListener {
                val Editor:SharedPreferences.Editor = sharedPred.edit()
                Editor.putInt("nomor", 2)
                Editor.apply()
                startActivity(Intent(this@ContohActivity, NomorContohActivity::class.java))
            }
            cth3.setOnClickListener {
                val Editor:SharedPreferences.Editor = sharedPred.edit()
                Editor.putInt("nomor", 3)
                Editor.apply()
                startActivity(Intent(this@ContohActivity, NomorContohActivity::class.java))
            }

        }
    }
}