package com.dhandev.eepa.asah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.paris.extensions.style
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityDialogHasilBinding

class DialogHasilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDialogHasilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogHasilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.rincianHasil)

        binding.apply {
            val noSoal = intent.getStringExtra("noSoal")
            val hasil = intent.getIntExtra("hasilPeriksa", 0)
            val soal = intent.getStringExtra("soal")
            val jawabanmu = intent.getStringExtra("jawabanMu")
            val kunci = intent.getStringExtra("kunci")

            soalKe.text = getString(R.string.soalKe, noSoal)
            hasilPeriksa.style(hasil)
            isiSoal.text = soal
            jawabanMu.text = jawabanmu
            kunciJawaban.text = kunci
        }
    }
}