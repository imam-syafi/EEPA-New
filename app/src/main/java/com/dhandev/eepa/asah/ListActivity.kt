package com.dhandev.eepa.asah

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityListBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.lang.reflect.Type

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "listPernyataan")

class ListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityListBinding
//    private lateinit var sharedPred : SharedPreferences
    var listPernyataan = mutableListOf<String>()
    var state = "Normal"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        sharedPred = this.getSharedPreferences("BenarSalah", MODE_PRIVATE)
//        loadPernyataan()

        binding.apply {
            arrowBack.setOnClickListener {
                if (state == "Normal"){
                    onBackPressed()
                } else {
                    arrowBack.setImageResource(R.drawable.arrow_back)
                    imageView4.setImageResource(R.drawable.news_bg)
                    title.setText("Daftar pernyataan")
                    state = "Normal"
                    isEditable(false)
                    fab.visibility = View.VISIBLE
                    lifecycleScope.launch {
                        save("1", soalPertama.text.toString())
                        save("2", soalKedua.text.toString())
                        save("3", soalKetiga.text.toString())
                        save("4", soalKeempat.text.toString())
                        save("5", soalKelima.text.toString())
                        save("6", soalKeenam.text.toString())
                        save("7", soalKetujuh.text.toString())
                    }
                    Toast.makeText(this@ListActivity, "Perubahan Tersimpan", Toast.LENGTH_SHORT).show()
                }
            }

            isEditable(false)

            fab.setOnClickListener {
                val isiSoall15 = soalKelimabelas.text.toString()
                arrowBack.setImageResource(R.drawable.done)
                imageView4.setImageResource(R.drawable.news_bg_green)
                title.setText("Mode Edit")
                state = "Tambah"
                isEditable(true)
                fab.visibility = View.GONE
            }

            lifecycleScope.launch {
                soalPertama.setText(load("1") ?: "Partikel Dasar sangat menarik!")
                soalKedua.setText(load("2") ?: "Fermion mematuhi statistika Fermi-Dirac")
                soalKetiga.setText(load("3") ?: "Gluon berperan sebagai lem antar partikel elementer pada Hadron")
                soalKeempat.setText(load("4") ?: "LHC singkatan dari Large Hadron Collider")
                soalKelima.setText(load("5")?: "Elektron adalah partikel elementer pertama yang ditemukan")
            }
        }
    }

    suspend private fun save(key : String, value : String) {
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit { list ->
            list[dataStoreKey] = value
        }
    }
    suspend private fun load(key : String) : String? {
        val dataStoreKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }


//    private fun loadPernyataan() {
//        val list = sharedPred.getString("list", "gagal").toString()
//        val listSplit = list.split(",").toTypedArray()
//        binding.apply {
//            soalPertama.setText(listSplit[0])
//            soalKedua.setText(listSplit[1])
//            soalKetiga.setText(listSplit[2])
//            soalKeempat.setText(listSplit[3])
//            soalKelima.setText(listSplit[4])
//        }
//    }

    private fun isEditable(state : Boolean) {
        binding.apply {
            soalPertama.isEnabled = state
            soalKedua.isEnabled = state
            soalKetiga.isEnabled = state
            soalKeempat.isEnabled = state
            soalKelima.isEnabled = state
            soalKeenam.isEnabled = state
            soalKetujuh.isEnabled = state
            soalKedelapan.isEnabled = state
            soalKesembilan.isEnabled = state
            soalKesepuluh.isEnabled = state
            soalKesebelas.isEnabled = state
            soalKeduabelas.isEnabled = state
            soalKetigaelas.isEnabled = state
            soalKeempatbelas.isEnabled = state
            soalKelimabelas.isEnabled = state

            if (state){
                soalKeenam.visibility = View.VISIBLE
                soalKetujuh.visibility = View.VISIBLE
                soalKedelapan.visibility = View.VISIBLE
                soalKesembilan.visibility = View.VISIBLE
                soalKesepuluh.visibility = View.VISIBLE
                soalKesebelas.visibility = View.VISIBLE
                soalKeduabelas.visibility = View.VISIBLE
                soalKetigaelas.visibility = View.VISIBLE
                soalKeempatbelas.visibility = View.VISIBLE
                soalKelimabelas.visibility = View.VISIBLE
            } else {
                terisi()
            }
        }
    }

    private fun terisi() {
        binding.apply {
            if (soalKelimabelas.text.isNotEmpty()){soalKelimabelas.visibility = View.VISIBLE}
            else if (soalKeempatbelas.text.isNotEmpty()){soalKelimabelas.visibility = View.GONE}
            else if (soalKetigaelas.text.isNotEmpty()){soalKeempatbelas.visibility = View.GONE
                soalKelimabelas.visibility = View.GONE}
            else if (soalKeduabelas.text.isNotEmpty()){soalKetigaelas.visibility = View.GONE
                soalKeempatbelas.visibility = View.GONE
                soalKelimabelas.visibility = View.GONE}
            else if (soalKesebelas.text.isNotEmpty()){soalKeduabelas.visibility = View.GONE
                soalKetigaelas.visibility = View.GONE
                soalKeempatbelas.visibility = View.GONE
                soalKelimabelas.visibility = View.GONE}
            else if (soalKesepuluh.text.isNotEmpty()){soalKesebelas.visibility = View.GONE
                soalKeduabelas.visibility = View.GONE
                soalKetigaelas.visibility = View.GONE
                soalKeempatbelas.visibility = View.GONE
                soalKelimabelas.visibility = View.GONE}
            else if (soalKesembilan.text.isNotEmpty()){soalKesepuluh.visibility = View.GONE
                soalKesebelas.visibility = View.GONE
                soalKeduabelas.visibility = View.GONE
                soalKetigaelas.visibility = View.GONE
                soalKeempatbelas.visibility = View.GONE
                soalKelimabelas.visibility = View.GONE}
            else if (soalKedelapan.text.isNotEmpty()){soalKesembilan.visibility = View.GONE
                soalKesepuluh.visibility = View.GONE
                soalKesebelas.visibility = View.GONE
                soalKeduabelas.visibility = View.GONE
                soalKetigaelas.visibility = View.GONE
                soalKeempatbelas.visibility = View.GONE
                soalKelimabelas.visibility = View.GONE}
            else if (soalKetujuh.text.isNotEmpty()){soalKedelapan.visibility = View.GONE
                soalKesembilan.visibility = View.GONE
                soalKesepuluh.visibility = View.GONE
                soalKesebelas.visibility = View.GONE
                soalKeduabelas.visibility = View.GONE
                soalKetigaelas.visibility = View.GONE
                soalKeempatbelas.visibility = View.GONE
                soalKelimabelas.visibility = View.GONE}
            else if (soalKeenam.text.isNotEmpty()){soalKetujuh.visibility = View.GONE
                soalKedelapan.visibility = View.GONE
                soalKesembilan.visibility = View.GONE
                soalKesepuluh.visibility = View.GONE
                soalKesebelas.visibility = View.GONE
                soalKeduabelas.visibility = View.GONE
                soalKetigaelas.visibility = View.GONE
                soalKeempatbelas.visibility = View.GONE
                soalKelimabelas.visibility = View.GONE}
        }
    }

//    override fun onBackPressed() {
////        val editor : SharedPreferences.Editor = sharedPred.edit()
//
//        binding.apply {
//            listPernyataan.set(0, soalPertama.text.toString())
//            listPernyataan.set(1, soalKedua.text.toString())
//            listPernyataan.set(2, soalKetiga.text.toString())
//            listPernyataan.set(3, soalKeempat.text.toString())
//            listPernyataan.set(4, soalKelima.text.toString())
//            listPernyataan.set(5, soalKeenam.text.toString())
//            listPernyataan.set(6, soalKetujuh.text.toString())
//
//            val listString = ArrayList(listPernyataan) //mutable list to array list
//
////            editor.putString("list", listString)
////            editor.apply()
//            startActivity(Intent(this@ListActivity, BenarSalahActivity::class.java))
//            finish()
//        }
//    }
}
