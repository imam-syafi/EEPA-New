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
        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }

        val list = listOf("Partikel Elementer", "Partikel Komposit")
        adapter1(list)
    }

    private fun adapter1(list : List<String>) {
        val adapter = ArrayAdapter(this, R.layout.list_item, list)
        val menuJenisDropdown = binding.menuJenis.editText as? AutoCompleteTextView
        menuJenisDropdown?.setAdapter(adapter)

        menuJenisDropdown?.setOnItemClickListener{parent, v, position, id ->
            val jenisTerpilih = adapter.getItem(position)
            val listElementer = listOf("Boson", "Fermion")
            val listKomposit = listOf("Baryons", "Mesons")
            when(jenisTerpilih){
                "Partikel Elementer" -> {
                    binding.menuJenis2.editText?.setText("Pilih jenis partikel")
                    binding.menuJenis2.hint = "Jenis Distribusi Partikel"
                    adapter2(listElementer)
                }
                "Partikel Komposit" -> {
                    binding.menuJenis2.editText?.setText("Pilih jenis distribusi")
                    binding.menuJenis2.hint = "Keluarga Hadron"
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
            val listFermion = listOf("Up", "Down", "Charm", "Strange", "Top", "Bottom", "Elektron", "Elektron Neutrino", "Muon", "Muon Neutrino", "Tau", "Tau Neutrino")
            val listBaryon = listOf("Proton", "Neutron", "Lambda", "Sigma", "Xi", "Omega-minus")
            when(jenisTerpilih){
                //partikel boson
                "Boson" -> adapter3(listBoson)      //jika adapter2 kita plilih boson, maka adapter 3 akan menampilkan listBoson
                "Fermion" -> adapter3(listFermion)
                //partikel baryon
                "Baryons" -> adapter3(listBaryon)
            }
        }
    }

    private fun adapter3(list: List<String>) {
        val adapter3 = ArrayAdapter(this, R.layout.list_item, list)
        val menuNamaDropdown = binding.menuNama.editText as? AutoCompleteTextView
        menuNamaDropdown?.setAdapter(adapter3)

        menuNamaDropdown?.setOnItemClickListener{parent, v, position, id ->
            val jenisTerpilih = adapter3.getItem(position)
            val listFoton = listOf("Boson", "Foton", "γ", "0", "Stabil", "0", "1", "Pembawa gaya elektromagnetik", "Albert Einstein (1905)\nNama \"foton\" umumnya dikaitkan dengan Gilbert N. Lewis (1926)")
            val listUp = listOf("Quark", "Up", "u", "1,8-3,0 Mev", "Variabel", "+2/3", "1/2", "Penyusun partikel komposit", "Teori pada tahun 1964 oleh Gell-Mann dan Zweig.\nObservasi pada 1968-72 saat hamburan elektron di SLAC dan hamburan neutrino di CERN")
            val listProton = listOf("Baryon", "Proton", "p", "938,3 Mev", "Stabil", "+1", "1/2", "Penyusun inti atom yang bermuatan positif", "1911-1919 oleh E.Rutherford")
            when(jenisTerpilih){
                "Foton" -> detailData(listFoton)
                "Proton" -> detailData(listProton)
                "Up" -> detailData(listUp)
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