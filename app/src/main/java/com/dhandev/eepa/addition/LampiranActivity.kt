package com.dhandev.eepa.addition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dhandev.eepa.databinding.ActivityLampiranBinding
import com.dhandev.eepa.materi.MateriTimeline

class LampiranActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLampiranBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLampiranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            arrowBack.setOnClickListener {
                onBackPressed()
            }
            timeline.setOnClickListener { startActivity(Intent(this@LampiranActivity, MateriTimeline::class.java)) }
        }
    }
}