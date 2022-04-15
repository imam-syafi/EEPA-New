package com.dhandev.eepa.asah

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityContohBinding
import com.dhandev.eepa.databinding.ActivityNomorContohBinding

class NomorContohActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNomorContohBinding
    private lateinit var sharedPred : SharedPreferences
    var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNomorContohBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPred = this.getSharedPreferences("Contoh Soal", MODE_PRIVATE)

        page = sharedPred.getInt("nomor", 1)

        binding.apply {
            arrowBack.setOnClickListener {
                onBackPressed()
            }
            btnShow.setOnClickListener {
                if (solusi.isVisible){
                    solusi.visibility = View.GONE
                } else {
                    solusi.visibility = View.VISIBLE
                }
            }
            btnNext.setOnClickListener {
                when(page){
                    1 -> contohSoal2()
                    2 -> contohSoal3()
                    3 -> Toast.makeText(this@NomorContohActivity, "Coming Soon", Toast.LENGTH_LONG).show()
                }
            }
            btnPrev.visibility = View.GONE
            btnPrev.setOnClickListener {
                when(page){
                    2 -> contohSoal1()
                    3 -> contohSoal2()
                }
            }
        }
        when(page){
            2 -> contohSoal2()
            3 -> contohSoal3()
        }
    }

    private fun contohSoal1() {
        page = 1
        binding.apply {
            nomorSoal.text = "Soal nomor $page"
            btnPrev.visibility = View.GONE
            soal.text = getString(R.string.soal_contoh_1)
            solusi.visibility = View.GONE
            solusi.text = getString(R.string.solusi_contoh_1)
        }    }

    private fun contohSoal2() {
        page = 2
        binding.apply {
            nomorSoal.text = "Soal nomor $page"
            btnPrev.visibility = View.VISIBLE
            soal.text = "Sebutkan partikel elementer yang termasuk partikel Fermion!"
            solusi.visibility = View.GONE
            solusi.text = "Golongan Lepton : \nElektron, Muon, Tau, beserta masing-masing antipartikelnya \nGolongan Quark : \nUp, Down, Charm, Strange, Top, dan Bottom"
        }
    }


    private fun contohSoal3() {
        page = 3
        binding.apply {
            nomorSoal.text = "Soal nomor $page"
            btnPrev.visibility = View.VISIBLE
            soal.text = "Sebutkan partikel elementer yang termasuk partikel Boson!"
            solusi.visibility = View.GONE
            solusi.text = "Golongan Gauge/ Vektor : \nGluon, Foton, Z boson, W boson \nGolongan Skalar : \nHiggs Boson"
        }
    }
}