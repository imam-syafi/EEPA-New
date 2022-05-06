package com.dhandev.eepa.asah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.dhandev.eepa.databinding.ActivityMulaiBenarSalahBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MulaiBenarSalahActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMulaiBenarSalahBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMulaiBenarSalahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            arrowBack.setOnClickListener { onBackPressed() }
            lifecycleScope.launch {
                pernyataan.setText(load("1") ?: "Partikel Dasar sangat menarik!")
            }
        }
    }

    suspend private fun load(key : String) : String? {
        val dataStoreKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }
}