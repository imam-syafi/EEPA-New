package com.dhandev.eepa.asah

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
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
                if (table.isVisible){
                    btnShow.text = getString(R.string.tampilkan_jawaban)
                    table.visibility = View.GONE
                    cleanAnswer()
                } else {
                    btnShow.text = getString(R.string.sembunyikan_jawaban)
                    table.visibility = View.VISIBLE
                    when(page){
                        1 -> pilihan2.setTextColor(ContextCompat.getColor(this@NomorContohActivity, R.color.greenBright))
                        2 -> pilihan1.setTextColor(ContextCompat.getColor(this@NomorContohActivity, R.color.greenBright))
                        3 -> pilihan3.setTextColor(ContextCompat.getColor(this@NomorContohActivity, R.color.greenBright))
                        4 -> pilihan2.setTextColor(ContextCompat.getColor(this@NomorContohActivity, R.color.greenBright))
                        5 -> pilihan4.setTextColor(ContextCompat.getColor(this@NomorContohActivity, R.color.greenBright))
                    }
                }
            }
            btnNext.setOnClickListener {
                cleanAnswer()
                when(page){
                    1 -> contohSoal2()
                    2 -> contohSoal3()
                    3 -> contohSoal4()
                    4 -> contohSoal5()
                }
            }
            btnPrev.visibility = View.GONE
            btnPrev.setOnClickListener {
                cleanAnswer()
                when(page){
                    2 -> contohSoal1()
                    3 -> contohSoal2()
                    4 -> contohSoal3()
                    5 -> contohSoal4()
                }
            }
        }
        when(page){
            1 -> contohSoal1()
            2 -> contohSoal2()
            3 -> contohSoal3()
            4 -> contohSoal3()
            5 -> contohSoal3()
        }
    }

    private fun cleanAnswer() {
        binding.apply {
            pilihan2.setTextColor(ContextCompat.getColor(this@NomorContohActivity, R.color.abu))
            pilihan1.setTextColor(ContextCompat.getColor(this@NomorContohActivity, R.color.abu))
            pilihan3.setTextColor(ContextCompat.getColor(this@NomorContohActivity, R.color.abu))
            pilihan2.setTextColor(ContextCompat.getColor(this@NomorContohActivity, R.color.abu))
            pilihan4.setTextColor(ContextCompat.getColor(this@NomorContohActivity, R.color.abu))
        }
    }

    private fun contohSoal1() {
        page = 1
        binding.apply {
            nomorSoal.text = "Soal nomor $page"
            btnPrev.visibility = View.GONE
            table.visibility = View.GONE
            soal.text = getString(R.string.contoh_soal_1)
            pilihan1.text = getString(R.string.opsi_soal_1_1)
            pilihan2.text = getString(R.string.opsi_soal_1_2)
            pilihan3.text = getString(R.string.opsi_soal_1_3)
            pilihan4.text = getString(R.string.opsi_soal_1_4)
            poin1.text = getString(R.string.solusi_contoh_1)
            poin2.text = getString(R.string.solusi_contoh_1_2)
            poin3.text = getString(R.string.solusi_contoh_1_3)
            poin4.text = getString(R.string.solusi_contoh_1_4)
            poin5.text = getString(R.string.solusi_contoh_1_5)
            row5.visibility = View.VISIBLE
        }
    }

    private fun contohSoal2() {
        page = 2
        binding.apply {
            nomorSoal.text = "Soal nomor $page"
            btnPrev.visibility = View.VISIBLE
            table.visibility = View.GONE
            soal.text = getString(R.string.contoh_soal_2)
            pilihan1.text = getString(R.string.opsi_soal_2_1)
            pilihan2.text = getString(R.string.opsi_soal_2_2)
            pilihan3.text = getString(R.string.opsi_soal_2_3)
            pilihan4.text = getString(R.string.opsi_soal_2_4)
            poin1.text = getString(R.string.solusi_contoh_2)
            poin2.text = getString(R.string.solusi_contoh_2_2)
            poin3.text = getString(R.string.solusi_contoh_2_3)
            poin4.text = getString(R.string.solusi_contoh_2_4)
            row5.visibility = View.GONE
        }
    }


    private fun contohSoal3() {
        page = 3
        binding.apply {
            nomorSoal.text = "Soal nomor $page"
            btnPrev.visibility = View.VISIBLE
            table.visibility = View.GONE
            soal.text = getString(R.string.contoh_soal_3)
            pilihan1.text = getString(R.string.opsi_soal_3_1)
            pilihan2.text = getString(R.string.opsi_soal_3_2)
            pilihan3.text = getString(R.string.opsi_soal_3_3)
            pilihan4.text = getString(R.string.opsi_soal_3_4)
            poin1.text = getString(R.string.solusi_contoh_3)
            poin2.text = getString(R.string.solusi_contoh_3_2)
            poin3.text = getString(R.string.solusi_contoh_3_3)
            poin4.text = getString(R.string.solusi_contoh_3_4)
            poin5.text = getString(R.string.solusi_contoh_3_5)
            row5.visibility = View.VISIBLE
        }
    }

    private fun contohSoal4() {
        page = 4
        binding.apply {
            nomorSoal.text = "Soal nomor $page"
            btnPrev.visibility = View.VISIBLE
            table.visibility = View.GONE
            soal.text = getString(R.string.contoh_soal_4)
            pilihan1.text = getString(R.string.opsi_soal_4_1)
            pilihan2.text = getString(R.string.opsi_soal_4_2)
            pilihan3.text = getString(R.string.opsi_soal_4_3)
            pilihan4.text = getString(R.string.opsi_soal_4_4)
            poin1.text = getString(R.string.solusi_contoh_4)
            poin2.text = getString(R.string.solusi_contoh_4_2)
            poin3.text = getString(R.string.solusi_contoh_4_3)
            poin4.text = getString(R.string.solusi_contoh_4_4)
            row5.visibility = View.GONE
        }
    }

    private fun contohSoal5() {
        page = 5
        binding.apply {
            nomorSoal.text = "Soal nomor $page"
            btnPrev.visibility = View.VISIBLE
            btnNext.visibility = View.GONE
            table.visibility = View.GONE
            soal.text = getString(R.string.contoh_soal_5)
            pilihan1.text = getString(R.string.opsi_soal_5_1)
            pilihan2.text = getString(R.string.opsi_soal_5_2)
            pilihan3.text = getString(R.string.opsi_soal_5_3)
            pilihan4.text = getString(R.string.opsi_soal_5_4)
            poin1.text = getString(R.string.solusi_contoh_5)
            poin2.text = getString(R.string.solusi_contoh_5_2)
            poin3.text = getString(R.string.solusi_contoh_5_3)
            poin4.text = getString(R.string.solusi_contoh_5_4)
            row5.visibility = View.GONE
        }
    }
}