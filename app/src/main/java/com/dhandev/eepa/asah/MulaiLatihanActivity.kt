package com.dhandev.eepa.asah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dhandev.eepa.databinding.ActivityLatihanBinding
import com.dhandev.eepa.databinding.ActivityMulaiLatihanBinding
import kotlin.random.Random

class MulaiLatihanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMulaiLatihanBinding
    var mutableList = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    var skor = 0
    var nomorSoal = 2
    var soal = Random.nextInt(1,11)
    var answer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMulaiLatihanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        randomize(soal)
        showSoal(soal)

        binding.apply {
            arrowBack.setOnClickListener {
                onBackPressed()
            }

//            btnNext.setOnClickListener {
//                soal = Random.nextInt(1,11)
//                randomize(soal)
//                title.text = "Nomor ${nomorSoal++}/10"
//            }


        }
    }
    fun randomize(soal : Int) {
//        var arr: IntArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
        if (mutableList.contains(soal)){
//            Toast.makeText(this, "Nomor $soal ada pada Array", Toast.LENGTH_SHORT).show()
            showSoal(soal)
            mutableList.remove(soal)
//            binding.soalLatihan.text = mutableList.toString()
        } else if (!mutableList.contains(soal)){
            randomize(Random.nextInt(1,11))
        } else if (mutableList.isEmpty()){
            Toast.makeText(this, "Sudah nomor terakhir", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showSoal(soal: Int) {
        binding.apply {
            when(soal){
                1 -> SoalPertama()
                2 -> SoalKedua()
                3 -> SoalKetiga()
                4 -> SoalKeempat()
                5 -> SoalKelima()
                6 -> SoalKeenam()
                7 -> SoalKetujuh()
                8 -> SoalKedelapan()
                9 -> SoalKesembilan()
                10 -> SoalKesepuluh()

            }
        }
    }

    private fun SoalKesepuluh() {
        binding.apply {
            soalLatihan.text = "Ini soal kesepuluh"
            opsi1.text = "Ini jawaban salah"
            opsi2.text = "Ini jawaban benar"
            opsi3.text = "Ini jawaban salah"
            opsi4.text = "Ini jawaban salah"

            btnNext.setOnClickListener {
                if (opsi2.isChecked){
                    answer = 1
                    nextQuestion(answer)
                } else if (pilihan.checkedRadioButtonId == -1) {
                    Toast.makeText(this@MulaiLatihanActivity, "Silahkan pilih jawaban", Toast.LENGTH_SHORT).show()
                } else {
                    answer = 0
                    nextQuestion(answer)
                }
            }
        }
    }

    private fun SoalKesembilan() {
        binding.apply {
            soalLatihan.text = "Ini soal kesembilan"
            opsi1.text = "Ini jawaban salah"
            opsi2.text = "Ini jawaban salah"
            opsi3.text = "Ini jawaban benar"
            opsi4.text = "Ini jawaban salah"

            btnNext.setOnClickListener {
                if (opsi3.isChecked){
                    answer = 1
                    nextQuestion(answer)
                } else if (pilihan.checkedRadioButtonId == -1) {
                    Toast.makeText(this@MulaiLatihanActivity, "Silahkan pilih jawaban", Toast.LENGTH_SHORT).show()
                } else {
                    answer = 0
                    nextQuestion(answer)
                }
            }
        }
    }

    private fun SoalKedelapan() {
        binding.apply {
            soalLatihan.text = "Ini soal kedelapan"
            opsi1.text = "Ini jawaban salah"
            opsi2.text = "Ini jawaban salah"
            opsi3.text = "Ini jawaban salah"
            opsi4.text = "Ini jawaban benar"

            btnNext.setOnClickListener {
                if (opsi4.isChecked){
                    answer = 1
                    nextQuestion(answer)
                } else if (pilihan.checkedRadioButtonId == -1) {
                    Toast.makeText(this@MulaiLatihanActivity, "Silahkan pilih jawaban", Toast.LENGTH_SHORT).show()
                } else {
                    answer = 0
                    nextQuestion(answer)
                }
            }
        }
    }

    private fun SoalKetujuh() {
        binding.apply {
            soalLatihan.text = "Ini soal ketujuh"
            opsi1.text = "Ini jawaban benar"
            opsi2.text = "Ini jawaban salah"
            opsi3.text = "Ini jawaban salah"
            opsi4.text = "Ini jawaban salah"

            btnNext.setOnClickListener {
                if (opsi1.isChecked){
                    answer = 1
                    nextQuestion(answer)
                } else if (pilihan.checkedRadioButtonId == -1) {
                    Toast.makeText(this@MulaiLatihanActivity, "Silahkan pilih jawaban", Toast.LENGTH_SHORT).show()
                } else {
                    answer = 0
                    nextQuestion(answer)
                }
            }
        }
    }

    private fun SoalKeenam() {
        binding.apply {
            soalLatihan.text = "Ini soal keenam"
            opsi1.text = "Ini jawaban salah"
            opsi2.text = "Ini jawaban benar"
            opsi3.text = "Ini jawaban salah"
            opsi4.text = "Ini jawaban salah"

            btnNext.setOnClickListener {
                if (opsi2.isChecked){
                    answer = 1
                    nextQuestion(answer)
                } else if (pilihan.checkedRadioButtonId == -1) {
                    Toast.makeText(this@MulaiLatihanActivity, "Silahkan pilih jawaban", Toast.LENGTH_SHORT).show()
                } else {
                    answer = 0
                    nextQuestion(answer)
                }
            }
        }
    }

    private fun SoalKelima() {
        binding.apply {
            soalLatihan.text = "Ini soal kelima"
            opsi1.text = "Ini jawaban salah"
            opsi2.text = "Ini jawaban salah"
            opsi3.text = "Ini jawaban benar"
            opsi4.text = "Ini jawaban salah"

            btnNext.setOnClickListener {
                if (opsi3.isChecked){
                    answer = 1
                    nextQuestion(answer)
                } else if (pilihan.checkedRadioButtonId == -1) {
                    Toast.makeText(this@MulaiLatihanActivity, "Silahkan pilih jawaban", Toast.LENGTH_SHORT).show()
                } else {
                    answer = 0
                    nextQuestion(answer)
                }
            }
        }
    }

    private fun SoalKeempat() {
        binding.apply {
            soalLatihan.text = "Ini soal keempat"
            opsi1.text = "Ini jawaban salah"
            opsi2.text = "Ini jawaban salah"
            opsi3.text = "Ini jawaban salah"
            opsi4.text = "Ini jawaban benar"

            btnNext.setOnClickListener {
                if (opsi4.isChecked){
                    answer = 1
                    nextQuestion(answer)
                } else if (pilihan.checkedRadioButtonId == -1) {
                    Toast.makeText(this@MulaiLatihanActivity, "Silahkan pilih jawaban", Toast.LENGTH_SHORT).show()
                } else {
                    answer = 0
                    nextQuestion(answer)
                }
            }
        }
    }

    private fun SoalKetiga() {
        binding.apply {
            soalLatihan.text = "Ini soal ketiga"
            opsi1.text = "Ini jawaban salah"
            opsi2.text = "Ini jawaban benar"
            opsi3.text = "Ini jawaban salah"
            opsi4.text = "Ini jawaban salah"

            btnNext.setOnClickListener {
                if (opsi2.isChecked){
                    answer = 1
                    nextQuestion(answer)
                } else if (pilihan.checkedRadioButtonId == -1) {
                    Toast.makeText(this@MulaiLatihanActivity, "Silahkan pilih jawaban", Toast.LENGTH_SHORT).show()
                } else {
                    answer = 0
                    nextQuestion(answer)
                }
            }
        }
    }

    private fun SoalKedua() {
        binding.apply {
            soalLatihan.text = "Ini soal kedua"
            opsi1.text = "Ini jawaban salah"
            opsi2.text = "Ini jawaban benar"
            opsi3.text = "Ini jawaban salah"
            opsi4.text = "Ini jawaban salah"

            btnNext.setOnClickListener {
                if (opsi2.isChecked){
                    answer = 1
                    nextQuestion(answer)
                } else if (pilihan.checkedRadioButtonId == -1) {
                    Toast.makeText(this@MulaiLatihanActivity, "Silahkan pilih jawaban", Toast.LENGTH_SHORT).show()
                } else {
                    answer = 0
                    nextQuestion(answer)
                }
            }
        }
    }

    private fun SoalPertama() {
        binding.apply {
            soalLatihan.text = "Ini soal pertama"
            opsi1.text = "Ini jawaban benar"
            opsi2.text = "Ini jawaban salah"
            opsi3.text = "Ini jawaban salah"
            opsi4.text = "Ini jawaban salah"

            btnNext.setOnClickListener {
                if (opsi1.isChecked){
                    answer = 1
                    nextQuestion(answer)
                } else if (pilihan.checkedRadioButtonId == -1) {
                    Toast.makeText(this@MulaiLatihanActivity, "Silahkan pilih jawaban", Toast.LENGTH_SHORT).show()
                } else {
                    answer = 0
                    nextQuestion(answer)
                }
            }
        }
    }

    private fun nextQuestion(answer : Int) {
        binding.apply {
            if (answer == 1) {
                skor++
                skorTotal.text = "Skor : $skor"
            } else {
                skorTotal.text = "Skor : $skor"
            }
            pilihan.clearCheck()
            if (nomorSoal < 10){
                soal = Random.nextInt(1,11)
                randomize(soal)
                title.text = "Nomor ${nomorSoal++}/10"
            } else if (nomorSoal == 10) {
                soal = Random.nextInt(1,11)
                nomorSoal++
                randomize(soal)
                title.text = "Nomor 10/10"
                btnNext.text = "Kumpulkan"
            } else {
                Toast.makeText(this@MulaiLatihanActivity, "Skor total : $skor", Toast.LENGTH_SHORT).show()
            }
        }
    }
}