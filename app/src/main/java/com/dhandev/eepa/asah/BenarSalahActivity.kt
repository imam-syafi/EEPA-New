package com.dhandev.eepa.asah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dhandev.eepa.databinding.ActivityBenarSalahBinding

class BenarSalahActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBenarSalahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBenarSalahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            arrowBack.setOnClickListener {
                onBackPressed()
            }
            daftar.setOnClickListener {
                startActivity(Intent(this@BenarSalahActivity, ListActivity::class.java))
            }
        }
    }
}