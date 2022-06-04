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

            cth1.text = "1. " + getString(R.string.contoh_soal_1)
            cth2.text = "2. " + getString(R.string.contoh_soal_2)
            cth3.text = "3. " + getString(R.string.contoh_soal_3)
            cth4.text = "4. " + getString(R.string.contoh_soal_4)
            cth5.text = "5. " + getString(R.string.contoh_soal_5)

            val Editor:SharedPreferences.Editor = sharedPred.edit()
            arrowBack.setOnClickListener {
                onBackPressed()
            }
            cth1.setOnClickListener {
                Editor.putInt("nomor", 1)
                Editor.apply()
                startActivity(Intent(this@ContohActivity, NomorContohActivity::class.java))
            }
            cth2.setOnClickListener {
                Editor.putInt("nomor", 2)
                Editor.apply()
                startActivity(Intent(this@ContohActivity, NomorContohActivity::class.java))
            }
            cth3.setOnClickListener {
                Editor.putInt("nomor", 3)
                Editor.apply()
                startActivity(Intent(this@ContohActivity, NomorContohActivity::class.java))
            }
            cth4.setOnClickListener {
                Editor.putInt("nomor", 4)
                Editor.apply()
                startActivity(Intent(this@ContohActivity, NomorContohActivity::class.java))
            }
            cth5.setOnClickListener {
                Editor.putInt("nomor", 5)
                Editor.apply()
                startActivity(Intent(this@ContohActivity, NomorContohActivity::class.java))
            }

        }
    }
}