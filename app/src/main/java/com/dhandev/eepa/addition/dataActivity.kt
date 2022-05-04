package com.dhandev.eepa.addition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.airbnb.paris.R2.id.list_item
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityDataBinding

class dataActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = listOf("Partikel Elementer", "Partikel Komposit")
        adapter1(list)
    }

    private fun adapter1(list : List<String>) {
        val adapter = ArrayAdapter(this, R.layout.list_item, list)
        val menuJenisDropdown = binding.menuJenis.editText as? AutoCompleteTextView
        menuJenisDropdown?.setAdapter(adapter)

        menuJenisDropdown?.setOnItemClickListener{parent, v, position, id ->
            val jenisTerpilih = adapter.getItem(position)
            val listElementer = listOf("Boson", "Fermion", "Graviton")
            val listKomposit = listOf("Baryons", "Mesons")
            when(jenisTerpilih){
                "Partikel Elementer" -> {
                    binding.menuJenis2.editText?.setText("Pilih jenis partikel")
                    adapter2(listElementer)
                }
                "Partikel Komposit" -> {
                    binding.menuJenis2.editText?.setText("Pilih jenis distribusi")
                    adapter2(listKomposit)
                }
            }
        }
    }

    private fun adapter2(list: List<String>) {
        val adapter2 = ArrayAdapter(this, R.layout.list_item, list)
        val menuDistribusiDropdown = binding.menuJenis2.editText as? AutoCompleteTextView
        menuDistribusiDropdown?.setAdapter(adapter2)

        menuDistribusiDropdown?.setOnItemClickListener{parent, v, position, id ->
            val jenisTerpilih = adapter2.getItem(position)
            val listBoson = listOf("Foton", "W Boson", "Z Boson", "Gluon", "Higgs Boson")
            when(jenisTerpilih){
                "Boson" -> adapter3(listBoson)
            }
        }
    }

    private fun adapter3(list: List<String>) {
        val adapter3 = ArrayAdapter(this, R.layout.list_item, list)
        val menuNamaDropdown = binding.menuNama.editText as? AutoCompleteTextView
        menuNamaDropdown?.setAdapter(adapter3)

        menuNamaDropdown?.setOnItemClickListener{parent, v, position, id ->
            val jenisTerpilih = adapter3.getItem(position)
            val listFoton = listOf("Boson", "Foton", "Y", "0", "Stabil", "0", "1", "Pembawa gaya elektromagnetik", "1905 oleh Albert Einstein")
            when(jenisTerpilih){
                "Foton" -> detailData(listFoton)
            }
        }
    }

    private fun detailData(list: List<String>) {
        binding.apply {
            family.text = list[0]
            nama.text = list[1]
            simbol.text = list[2]
            massa.text = list[3]
            lifetime.text = list[4]
            muatan.text = list[5]
            spin.text = list[6]
            fungsi.text = list[7]
            ditemukan.text = list[8]
        }
    }

}