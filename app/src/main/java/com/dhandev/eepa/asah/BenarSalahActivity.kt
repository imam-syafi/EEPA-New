package com.dhandev.eepa.asah

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.dhandev.eepa.databinding.ActivityBenarSalahBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class BenarSalahActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBenarSalahBinding
    private lateinit var sharedPref : SharedPreferences
    private lateinit var selectedItem: String
    private var selectedItemIndex: Int = 0
    private val items = arrayOf("3", "5", "10")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBenarSalahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = this.getSharedPreferences("BenarSalah", MODE_PRIVATE)

        binding.apply {
            arrowBack.setOnClickListener {
                onBackPressed()
            }
            daftar.setOnClickListener {
                startActivity(Intent(this@BenarSalahActivity, ListActivity::class.java))
            }
            btnMulai.setOnClickListener {
                startActivity(Intent(this@BenarSalahActivity, MulaiBenarSalahActivity::class.java))
                finish()
            }
            lifecycleScope.launch {
                val skor = load("NilaiBS")?:"0"
                lastScore.setText("Skor terakhir : $skor")
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
                val Editor : SharedPreferences.Editor = sharedPref.edit()
                Editor.putString("terpilih", terpilih)
                Editor.apply()
            }
            .setNegativeButton("Batal") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    suspend private fun load(key : String) : String? {
        val dataStoreKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }
}