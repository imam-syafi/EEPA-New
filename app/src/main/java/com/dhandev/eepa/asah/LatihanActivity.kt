package com.dhandev.eepa.asah

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityLatihanBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.DecimalFormat

class LatihanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLatihanBinding
    private lateinit var sharedPred : SharedPreferences
    private lateinit var selectedItem: String
    private var selectedItemIndex: Int = 0
    private val items = arrayOf("10", "15", "20")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLatihanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPred = this.getSharedPreferences("User", MODE_PRIVATE)

        val latest = sharedPred.getInt("latestScore", 0)
        val final : Double = (latest.toDouble()/150.0)*100.0
        val df : DecimalFormat = DecimalFormat("#.#")
        binding.apply {
            arrowBack.setOnClickListener {
                onBackPressed()
            }
            lastScore.text = "Skor terakhir : ${df.format(final)}"
            btnMulai.setOnClickListener {
                startActivity(Intent(this@LatihanActivity, MulaiLatihanActivity::class.java))
                finish()
            }
            timer.setOnClickListener {
                showOptionDialog()
            }
        }
    }

    private fun showOptionDialog() {
        selectedItem = items[selectedItemIndex]

        MaterialAlertDialogBuilder(this)
            .setTitle("Pilih Durasi Pengerjaan (menit)")
            .setSingleChoiceItems(items, selectedItemIndex){dialog, which ->
                selectedItemIndex = which
                selectedItem = items[which]
            }
            .setPositiveButton("Ok"){dialog, which ->
                val terpilih = selectedItem
                val Editor : SharedPreferences.Editor = sharedPred.edit()
                Editor.putString("terpilih", terpilih)
                Editor.apply()
            }
            .setNegativeButton("Batal") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }
}