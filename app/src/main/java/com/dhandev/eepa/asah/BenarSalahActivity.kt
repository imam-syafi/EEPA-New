package com.dhandev.eepa.asah

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.dhandev.eepa.databinding.ActivityBenarSalahBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class BenarSalahActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBenarSalahBinding
    private lateinit var sharedPref : SharedPreferences

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
                lastScore.setText("Skor terakhir : " +load("NilaiBS"))
            }

        }
    }

    suspend private fun load(key : String) : String? {
        val dataStoreKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }
}