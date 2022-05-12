package com.dhandev.eepa.asah

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
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
import dev.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.lang.reflect.Type

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "listPernyataan")

class ListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityListBinding
    var listPernyataan = mutableListOf<String>()
    var listJawaban = mutableListOf<String>()
    var state = "Normal"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            arrowBack.setOnClickListener {
                onBackPressed()
            }

            isEditable(false)

            fab.setOnClickListener {
                arrowBack.setImageResource(R.drawable.done)
                imageView4.setImageResource(R.drawable.news_bg_green)
                title.setText("Mode Edit")
                state = "Tambah"
                isEditable(true)
                fab.visibility = View.GONE
            }

            lifecycleScope.launch {
                soalPertama.setText(load("1") ?: resources.getString(R.string.default_1))
                soalKedua.setText(load("2") ?: resources.getString(R.string.default_2))
                soalKetiga.setText(load("3") ?: resources.getString(R.string.default_3))
                soalKeempat.setText(load("4") ?: resources.getString(R.string.default_4))
                soalKelima.setText(load("5")?: resources.getString(R.string.default_5))
                soalKeenam.setText(load("6") ?: resources.getString(R.string.default_6))
                soalKetujuh.setText(load("7") ?: resources.getString(R.string.default_7))
                soalKedelapan.setText(load("8") ?: resources.getString(R.string.default_8))
                soalKesembilan.setText(load("9") ?: resources.getString(R.string.default_9))
                soalKesepuluh.setText(load("10") ?: resources.getString(R.string.default_10))
                soalKesebelas.setText(load("11") ?: resources.getString(R.string.default_11))
                soalKeduabelas.setText(load("12") ?: resources.getString(R.string.default_12))
                soalKetigaelas.setText(load("13") ?: resources.getString(R.string.default_13))
                soalKeempatbelas.setText(load("14") ?: resources.getString(R.string.default_14))
                soalKelimabelas.setText(load("15") ?: resources.getString(R.string.default_15))
            }
            val opt_benarSalah = resources.getStringArray(R.array.benarSalah)
            val adapterSpinner = ArrayAdapter(this@ListActivity, R.layout.list_item, opt_benarSalah)
            jawabanPertama.adapter = adapterSpinner
            jawabanKedua.adapter = adapterSpinner
            jawabanKetiga.adapter = adapterSpinner
            jawabanKeempat.adapter = adapterSpinner
            jawabanKelima.adapter = adapterSpinner
            jawabanKeenam.adapter = adapterSpinner
            jawabanKetujuh.adapter = adapterSpinner
            jawabanKedelapan.adapter = adapterSpinner
            jawabanKesembilan.adapter = adapterSpinner
            jawabanKesepuluh.adapter = adapterSpinner
            jawabanKesebelas.adapter = adapterSpinner
            jawabanKeduabelas.adapter = adapterSpinner
            jawabanKetigabelas.adapter = adapterSpinner
            jawabanKeempatbelas.adapter = adapterSpinner
            jawabanKelimabelas.adapter = adapterSpinner

            //load saved kunci jawaban pada masing-masing spinner
            lifecycleScope.launch {
                load("j1")?: 1.let { jawabanPertama.setSelection(it)}
                load("j2")?: 0.let { jawabanKedua.setSelection(it) }
                load("j3")?: 0.let { jawabanKetiga.setSelection(it) }
                load("j4")?: 0.let { jawabanKeempat.setSelection(it) }
                load("j5")?: 0.let { jawabanKelima.setSelection(it) }
                load("j6")?: 1.let { jawabanKeenam.setSelection(it) }
                load("j7")?: 0.let { jawabanKetujuh.setSelection(it) }
                load("j8")?: 1.let { jawabanKedelapan.setSelection(it) }
                load("j9")?: 0.let { jawabanKesembilan.setSelection(it) }
                load("j10")?: 0.let { jawabanKesepuluh.setSelection(it) }
                load("j11")?: 1.let { jawabanKesebelas.setSelection(it) }
                load("j12")?: 1.let { jawabanKeduabelas.setSelection(it) }
                load("j13")?: 0.let { jawabanKetigabelas.setSelection(it) }
                load("j14")?: 0.let { jawabanKeempatbelas.setSelection(it) }
                load("j15")?: 1.let { jawabanKelimabelas.setSelection(it) }
            }
        }
    }

    private fun modeEdit() {
        binding.apply {
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
                save("8", soalKedelapan.text.toString())
                save("9", soalKesembilan.text.toString())
                save("10", soalKesepuluh.text.toString())
                save("11", soalKesebelas.text.toString())
                save("12", soalKeduabelas.text.toString())
                save("13", soalKetigaelas.text.toString())
                save("14", soalKeempatbelas.text.toString())
                save("15", soalKelimabelas.text.toString())
            }
            Toast.makeText(this@ListActivity, "Perubahan Tersimpan", Toast.LENGTH_SHORT).show()
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

    private fun isEditable(state : Boolean) {
        binding.apply {
            when(state){
                true -> {
                    soalPertama.isEnabled = true
                    soalKedua.isEnabled = true
                    soalKetiga.isEnabled = true
                    soalKeempat.isEnabled = true
                    soalKelima.isEnabled = true
                    soalKeenam.isEnabled = true
                    soalKetujuh.isEnabled = true
                    soalKedelapan.isEnabled = true
                    soalKesembilan.isEnabled = true
                    soalKesepuluh.isEnabled = true
                    soalKesebelas.isEnabled = true
                    soalKeduabelas.isEnabled = true
                    soalKetigaelas.isEnabled = true
                    soalKeempatbelas.isEnabled = true
                    soalKelimabelas.isEnabled = true

                    jawabanPertama.visibility = View.VISIBLE
                    jawabanKedua.visibility = View.VISIBLE
                    jawabanKetiga.visibility = View.VISIBLE
                    jawabanKeempat.visibility = View.VISIBLE
                    jawabanKelima.visibility = View.VISIBLE
                    jawabanKeenam.visibility = View.VISIBLE
                    jawabanKetujuh.visibility = View.VISIBLE
                    jawabanKedelapan.visibility = View.VISIBLE
                    jawabanKesembilan.visibility = View.VISIBLE
                    jawabanKesepuluh.visibility = View.VISIBLE
                    jawabanKesebelas.visibility = View.VISIBLE
                    jawabanKeduabelas.visibility = View.VISIBLE
                    jawabanKetigabelas.visibility = View.VISIBLE
                    jawabanKeempatbelas.visibility = View.VISIBLE
                    jawabanKelimabelas.visibility = View.VISIBLE
                }
                false -> {
                    soalPertama.isEnabled = false
                    soalKedua.isEnabled = false
                    soalKetiga.isEnabled = false
                    soalKeempat.isEnabled = false
                    soalKelima.isEnabled = false
                    soalKeenam.isEnabled = false
                    soalKetujuh.isEnabled = false
                    soalKedelapan.isEnabled = false
                    soalKesembilan.isEnabled = false
                    soalKesepuluh.isEnabled = false
                    soalKesebelas.isEnabled = false
                    soalKeduabelas.isEnabled = false
                    soalKetigaelas.isEnabled = false
                    soalKeempatbelas.isEnabled = false
                    soalKelimabelas.isEnabled = false

                    jawabanPertama.visibility = View.GONE
                    jawabanKedua.visibility = View.GONE
                    jawabanKetiga.visibility = View.GONE
                    jawabanKeempat.visibility = View.GONE
                    jawabanKelima.visibility = View.GONE
                    jawabanKeenam.visibility = View.GONE
                    jawabanKetujuh.visibility = View.GONE
                    jawabanKedelapan.visibility = View.GONE
                    jawabanKesembilan.visibility = View.GONE
                    jawabanKesepuluh.visibility = View.GONE
                    jawabanKesebelas.visibility = View.GONE
                    jawabanKeduabelas.visibility = View.GONE
                    jawabanKetigabelas.visibility = View.GONE
                    jawabanKeempatbelas.visibility = View.GONE
                    jawabanKelimabelas.visibility = View.GONE
                }
            }
        }
    }

    private fun terisi() {
        binding.apply {
            if (soalPertama.text.isEmpty()){soalPertama.error = "Harus diisi"}
            else if (soalKedua.text.isEmpty()){soalKedua.error = "Harus diisi"}
        }
    }

    override fun onBackPressed() {
       binding.apply {
           when{
               state == "Normal" -> {
                   super.onBackPressed()
               }
               soalPertama.text.isEmpty() -> soalPertama.error = "Harus diisi"
               soalKedua.text.isEmpty() -> soalKedua.error = "Harus diisi"
               else -> {
                   val BottomSheetDialog = BottomSheetMaterialDialog.Builder(this@ListActivity)
                       .setTitle("Simpan Perubahan")
                       .setMessage("Keluar dan simpan perubahan?")
                       .setCancelable(true)
                       .setPositiveButton("Simpan", R.drawable.ic_baseline_done_24) {dialog, which ->
                           terisi()
                           modeEdit()
                           kunciJawaban()
                           dialog.dismiss()
                       }
                       .setNegativeButton("Batal", R.drawable.ic_baseline_close_24) { dialog, which ->
                           dialog.dismiss()
                       }
                       .setAnimation("save.json")
                       .build()
                   BottomSheetDialog.show()
                   BottomSheetDialog.animationView.scaleType = ImageView.ScaleType.CENTER_INSIDE
               }
           }
       }
    }
    private fun kunciJawaban() {
        binding.apply {
            lifecycleScope.launch {
                save("j1",jawabanPertama.selectedItemPosition.toString())
                save("j2",jawabanKedua.selectedItemPosition.toString())
                save("j3",jawabanKetiga.selectedItemPosition.toString())
                save("j4",jawabanKeempat.selectedItemPosition.toString())
                save("j5",jawabanKelima.selectedItemPosition.toString())
                save("j6",jawabanKeenam.selectedItemPosition.toString())
                save("j7",jawabanKetujuh.selectedItemPosition.toString())
                save("j8",jawabanKedelapan.selectedItemPosition.toString())
                save("j9",jawabanKesembilan.selectedItemPosition.toString())
                save("j10",jawabanKesepuluh.selectedItemPosition.toString())
                save("j11",jawabanKesebelas.selectedItemPosition.toString())
                save("j12",jawabanKeduabelas.selectedItemPosition.toString())
                save("j13",jawabanKetigabelas.selectedItemPosition.toString())
                save("j14",jawabanKeempatbelas.selectedItemPosition.toString())
                save("j15",jawabanKelimabelas.selectedItemPosition.toString())
            }
        }
    }
}
