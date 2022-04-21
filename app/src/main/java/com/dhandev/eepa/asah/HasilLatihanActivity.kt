package com.dhandev.eepa.asah

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.airbnb.paris.extensions.style
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityHasilLatihanBinding

class HasilLatihanActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHasilLatihanBinding
    private lateinit var sharedPred : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilLatihanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var skor = intent.getIntExtra("skor", 0)
        binding.apply {
            sharedPred = this@HasilLatihanActivity.getSharedPreferences("User", MODE_PRIVATE)

            close.setOnClickListener {
                startActivity(Intent(this@HasilLatihanActivity, LatihanActivity::class.java))
                finish()
            }
            val username : String?  = sharedPred.getString("userName", null)
            descHasil.text = "Selamat $username, Kamu sudah berhasil menyelesaikan soal-soal latihan! \nBerikut rincian hasilnya:"
            soalPertama.text = "1. "+ soal(0)
            soalKedua.text = "2. "+ soal(1)
            soalKetiga.text = "3. "+ soal(2)
            soalKeempat.text = "4. "+ soal(3)
            soalKelima.text = "5. "+ soal(4)
            soalKeenam.text = "6. "+ soal(5)
            soalKetujuh.text = "7. "+ soal(6)
            soalKedelapan.text = "8. "+ soal(7)
            soalKesembilan.text = "9. "+ soal(8)
            soalKesepuluh.text = "10. "+ soal(9)

            val urutanJawaban = intent.getIntegerArrayListExtra("urutanJawaban")
            Toast.makeText(this@HasilLatihanActivity, urutanJawaban.toString(), Toast.LENGTH_SHORT).show()
            jawabanPertama.style(jawaban(0))
            jawabanKedua.style(jawaban(1))
            jawabanKetiga.style(jawaban(2))
            jawabanKeempat.style(jawaban(3))
            jawabanKelima.style(jawaban(4))
            jawabanKeenam.style(jawaban(5))
            jawabanKetujuh.style(jawaban(6))
            jawabanKedelapan.style(jawaban(7))
            jawabanKesembilan.style(jawaban(8))
            jawabanKesepuluh.style(jawaban(9))

            fireworks.setAnimation("fireworks.json")
            fireworks.playAnimation()

            skorTotal.text = skor.toString()
            when(skor){
                in 90..100 -> kataKata.text = "Luar biasa, pertahankan! 😍"
                in 70..80 -> kataKata.text = "Hebat, tingkatkan! ✨"
                in 60..70 -> kataKata.text = "Cukup baik, belajar lagi! 👍"
                else -> kataKata.text = "Kurang, belajar lebih giat lagi ya! 👌"
            }
            soalKesepuluh.setOnClickListener {
                Toast.makeText(this@HasilLatihanActivity, "Bisa ditekan kok! Mantap", Toast.LENGTH_SHORT).show()
            }

            val Editor:SharedPreferences.Editor = sharedPred.edit()
            Editor.putInt("latestScore", skor)
            Editor.apply()
        }
    }

    private fun jawaban(nomor : Int): Int {
        val urutanJawaban = intent.getIntegerArrayListExtra("urutanJawaban")
        var jawabannya = 0
        when(urutanJawaban?.get(nomor)){
            3 -> jawabannya = R.style.jawabanKosong
            1 -> jawabannya = R.style.jawabanBenar
            0 -> jawabannya = R.style.jawabanSalah
        }
        return jawabannya
    }

    private fun soal (nomor : Int) : CharSequence{
        val urutanSoal = intent.getIntegerArrayListExtra("urutanSoal")
        var isiSoal= "Ini soal"
        when(urutanSoal?.get(nomor)){
            1 -> isiSoal = "Ini soal pertama"
            2 -> isiSoal = "Ini soal kedua"
            3 -> isiSoal = "Ini soal ketiga"
            4 -> isiSoal = "Ini soal keempat"
            5 -> isiSoal = "Ini soal kelima"
            6 -> isiSoal = "Ini soal keenam"
            7 -> isiSoal = "Ini soal ketujuh"
            8 -> isiSoal = "Ini soal kedelapan"
            9 -> isiSoal = "Ini soal kesembilan"
            10 -> isiSoal = "Ini soal kesepuluh"
            99 -> isiSoal = "Soal belum diakses"
        }
        return isiSoal
    }

    //TODO:SHOW THE RIGHT ANSWER
    override fun onBackPressed() {
        startActivity(Intent(this@HasilLatihanActivity, LatihanActivity::class.java))
        finish()
    }
}