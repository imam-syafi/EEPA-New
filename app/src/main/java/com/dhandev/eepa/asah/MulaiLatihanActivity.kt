package com.dhandev.eepa.asah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.airbnb.paris.R2.id.time
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityLatihanBinding
import com.dhandev.eepa.databinding.ActivityMulaiLatihanBinding
import com.dhandev.eepa.onBoarding
import dev.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog
import kotlin.random.Random

class MulaiLatihanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMulaiLatihanBinding
    var mutableList = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    var listHasil = mutableListOf<Int>()
    var listJawaban = mutableListOf<Int>()
    var skor = 0
    var nomorSoal = 2
    var soal = Random.nextInt(1,11)
    var answer = 0
    lateinit var countdown_timer: CountDownTimer
    var time_in_milli_seconds = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMulaiLatihanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        randomize(soal)
        showSoal(soal)

        val time = 1 //in minute
        time_in_milli_seconds = time.toLong() *10000L
        startTimer(time_in_milli_seconds)

        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun startTimer(timeInMilliSeconds: Long) {
        countdown_timer = object : CountDownTimer(timeInMilliSeconds, 1000){
            override fun onTick(p0: Long) {
                time_in_milli_seconds = p0
                updateTextUI()
            }

            override fun onFinish() {
                listHasil.removeAt(0)
                defaultListHasil()
                defaultListJawaban()
                val intent = Intent(this@MulaiLatihanActivity, HasilLatihanActivity::class.java)
                val arrayListHasil = ArrayList(listHasil)
                val arrayListJawaban = ArrayList(listJawaban)
                intent.putIntegerArrayListExtra("urutanSoal", arrayListHasil)
                intent.putIntegerArrayListExtra("urutanJawaban", arrayListJawaban)
                intent.putExtra("skor", skor)
                startActivity(intent)
                finish()
                Toast.makeText(this@MulaiLatihanActivity, "Waktu Habis!", Toast.LENGTH_SHORT).show()
            }
        }
        countdown_timer.start()
    }

    private fun defaultListJawaban() {
        listJawaban.add(3)
        listJawaban.add(3)
        listJawaban.add(3)
        listJawaban.add(3)
        listJawaban.add(3)
        listJawaban.add(3)
        listJawaban.add(3)
        listJawaban.add(3)
        listJawaban.add(3)
        listJawaban.add(3)
    }

    private fun defaultListHasil() {
        listHasil.add(99)
        listHasil.add(99)
        listHasil.add(99)
        listHasil.add(99)
        listHasil.add(99)
        listHasil.add(99)
        listHasil.add(99)
        listHasil.add(99)
        listHasil.add(99)
        listHasil.add(99)
    }

    private fun updateTextUI() {
        val minute = (time_in_milli_seconds / 1000) / 60
        val seconds = (time_in_milli_seconds / 1000) % 60

        binding.timer.text = "$minute:$seconds"
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

            listHasil.add(10)
            btnNext.setOnClickListener {
                if (opsi2.isChecked){
                    answer = 1
                    listJawaban.add(1)
                    nextQuestion(answer)
                } else if (pilihan.checkedRadioButtonId == -1) {
                    Toast.makeText(this@MulaiLatihanActivity, "Silahkan pilih jawaban", Toast.LENGTH_SHORT).show()
                } else {
                    answer = 0
                    listJawaban.add(0)
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

            listHasil.add(9)
            btnNext.setOnClickListener {
                if (opsi3.isChecked){
                    answer = 1
                    listJawaban.add(1)
                    nextQuestion(answer)
                } else if (pilihan.checkedRadioButtonId == -1) {
                    Toast.makeText(this@MulaiLatihanActivity, "Silahkan pilih jawaban", Toast.LENGTH_SHORT).show()
                } else {
                    answer = 0
                    listJawaban.add(0)
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

            listHasil.add(8)
            btnNext.setOnClickListener {
                if (opsi4.isChecked){
                    answer = 1
                    listJawaban.add(1)
                    nextQuestion(answer)
                } else if (pilihan.checkedRadioButtonId == -1) {
                    Toast.makeText(this@MulaiLatihanActivity, "Silahkan pilih jawaban", Toast.LENGTH_SHORT).show()
                } else {
                    answer = 0
                    listJawaban.add(0)
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

            listHasil.add(7)
            btnNext.setOnClickListener {
                if (opsi1.isChecked){
                    answer = 1
                    listJawaban.add(1)
                    nextQuestion(answer)
                } else if (pilihan.checkedRadioButtonId == -1) {
                    Toast.makeText(this@MulaiLatihanActivity, "Silahkan pilih jawaban", Toast.LENGTH_SHORT).show()
                } else {
                    answer = 0
                    listJawaban.add(0)
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

            listHasil.add(6)
            btnNext.setOnClickListener {
                if (opsi2.isChecked){
                    answer = 1
                    listJawaban.add(1)
                    nextQuestion(answer)
                } else if (pilihan.checkedRadioButtonId == -1) {
                    Toast.makeText(this@MulaiLatihanActivity, "Silahkan pilih jawaban", Toast.LENGTH_SHORT).show()
                } else {
                    answer = 0
                    listJawaban.add(0)
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

            listHasil.add(5)
            btnNext.setOnClickListener {
                if (opsi3.isChecked){
                    answer = 1
                    listJawaban.add(1)
                    nextQuestion(answer)
                } else if (pilihan.checkedRadioButtonId == -1) {
                    Toast.makeText(this@MulaiLatihanActivity, "Silahkan pilih jawaban", Toast.LENGTH_SHORT).show()
                } else {
                    answer = 0
                    listJawaban.add(0)
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

            listHasil.add(4)
            btnNext.setOnClickListener {
                if (opsi4.isChecked){
                    answer = 1
                    listJawaban.add(1)
                    nextQuestion(answer)
                } else if (pilihan.checkedRadioButtonId == -1) {
                    Toast.makeText(this@MulaiLatihanActivity, "Silahkan pilih jawaban", Toast.LENGTH_SHORT).show()
                } else {
                    answer = 0
                    listJawaban.add(0)
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

            listHasil.add(3)
            btnNext.setOnClickListener {
                if (opsi2.isChecked){
                    answer = 1
                    listJawaban.add(1)
                    nextQuestion(answer)
                } else if (pilihan.checkedRadioButtonId == -1) {
                    Toast.makeText(this@MulaiLatihanActivity, "Silahkan pilih jawaban", Toast.LENGTH_SHORT).show()
                } else {
                    answer = 0
                    listJawaban.add(0)
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

            listHasil.add(2)
            btnNext.setOnClickListener {
                if (opsi2.isChecked){
                    answer = 1
                    listJawaban.add(1)
                    nextQuestion(answer)
                } else if (pilihan.checkedRadioButtonId == -1) {
                    Toast.makeText(this@MulaiLatihanActivity, "Silahkan pilih jawaban", Toast.LENGTH_SHORT).show()
                } else {
                    answer = 0
                    listJawaban.add(0)
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

            listHasil.add(1)
            btnNext.setOnClickListener {
                if (opsi1.isChecked){
                    answer = 1
                    listJawaban.add(1)
                    nextQuestion(answer)
                } else if (pilihan.checkedRadioButtonId == -1) {
                    Toast.makeText(this@MulaiLatihanActivity, "Silahkan pilih jawaban", Toast.LENGTH_SHORT).show()
                } else {
                    answer = 0
                    listJawaban.add(0)
                    nextQuestion(answer)
                }
            }
        }
    }

    private fun nextQuestion(answer : Int) {
        binding.apply {
            if (answer == 1) {
                skor+=10
            } else {
                skor
            }
            pilihan.clearCheck()
            if (nomorSoal < 10){
                soal = Random.nextInt(1,11)
                randomize(soal)
                title.text = "Nomor ${nomorSoal++}/10"
//                Toast.makeText(this@MulaiLatihanActivity, listJawaban.toString(), Toast.LENGTH_SHORT).show()
            } else if (nomorSoal == 10) {
                soal = Random.nextInt(1,11)
                nomorSoal++
                randomize(soal)
                title.text = "Nomor 10/10"
                btnNext.text = "Kumpulkan"
//                Toast.makeText(this@MulaiLatihanActivity, listJawaban.toString(), Toast.LENGTH_SHORT).show()
            } else {
                countdown_timer.cancel()
                listHasil.removeAt(0)
                val intent = Intent(this@MulaiLatihanActivity, HasilLatihanActivity::class.java)
                val arrayListHasil = ArrayList(listHasil)
                val arrayListJawaban = ArrayList(listJawaban)
//                Toast.makeText(this@MulaiLatihanActivity, arrayListJawaban.toString(), Toast.LENGTH_SHORT).show()
                intent.putIntegerArrayListExtra("urutanSoal", arrayListHasil)
                intent.putIntegerArrayListExtra("urutanJawaban", arrayListJawaban)
                intent.putExtra("skor", skor)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onBackPressed() {
        val BottomSheetDialog = BottomSheetMaterialDialog.Builder(this)
            .setTitle("Akhiri Latihan?")
            .setMessage("Jawaban Anda tidak tersimpan, yakin keluar dari latihan?")
            .setCancelable(true)
            .setPositiveButton("Akhiri", R.drawable.ic_baseline_done_24) {dialog, which ->
                countdown_timer.cancel()
                super.onBackPressed() }
            .setNegativeButton("Batal", R.drawable.ic_baseline_close_24) { dialog, which ->
                dialog.dismiss()
            }
            .setAnimation("question.json")
            .build()
        BottomSheetDialog.show()
        BottomSheetDialog.animationView.scaleType = ImageView.ScaleType.CENTER_INSIDE
    }
}