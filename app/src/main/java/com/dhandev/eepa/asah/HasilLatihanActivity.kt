package com.dhandev.eepa.asah

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.paris.extensions.style
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityHasilLatihanBinding
import java.text.DecimalFormat

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
                onBackPressed()
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
            soalKesebelas.text = "11. "+ soal(10)
            soalKeduabelas.text = "12. "+ soal(11)
            soalKetigabelas.text = "13. "+ soal(12)
            soalKeempatbelas.text = "14. "+ soal(13)
            soalKelimabelas.text = "15. "+ soal(14)
            soalKeenambelas.text = "16. "+ soal(15)
            soalKetujuhbelas.text = "17. "+ soal(16)
            soalKedelapanbelas.text = "18. "+ soal(17)
            soalKesembilanbelas.text = "19. "+ soal(18)
            soalKeduapuluh.text = "20. "+ soal(19)

            val urutanJawaban = intent.getIntegerArrayListExtra("urutanJawaban")
//            Toast.makeText(this@HasilLatihanActivity, urutanJawaban.toString(), Toast.LENGTH_SHORT).show()
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
            jawabanKesebelas.style(jawaban(10))
            jawabanKeduabelas.style(jawaban(11))
            jawabanKetigabelas.style(jawaban(12))
            jawabanKeempatbelas.style(jawaban(13))
            jawabanKelimabelas.style(jawaban(14))
            jawabanKeenambelas.style(jawaban(15))
            jawabanKetujuhbelas.style(jawaban(16))
            jawabanKedelapanbelas.style(jawaban(17))
            jawabanKesembilanbelas.style(jawaban(18))
            jawabanKeduapuluh.style(jawaban(19))

            fireworks.setAnimation("fireworks.json")
            fireworks.playAnimation()

            val final : Double = (skor.toDouble()/200.0)*100.0
            val df : DecimalFormat = DecimalFormat("#.##")
            skorTotal.text = df.format(final).toString()
            when(final){
                in 85.0..100.0 -> {
                    skorTotal.setTextColor(Color.BLUE)
                    descHasil.text = "Luar biasa, pertahankan! 😍"
                }
                in 70.0..84.9 -> {
                    skorTotal.setTextColor(resources.getColor(R.color.right))
                    descHasil.text = "Hebat, tingkatkan! ✨"
                }
                in 55.0..69.9 -> {
                    skorTotal.setTextColor(resources.getColor(R.color.orange))
                    descHasil.text = "Cukup baik, belajar lagi! 👍"
                }
                else -> {
                    skorTotal.setTextColor(Color.RED)
                    descHasil.text = "Kurang, belajar lebih giat lagi ya! 👌"
                }
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
            1 -> isiSoal = getString(R.string.soal_1)
            2 -> isiSoal = getString(R.string.soal_2)
            3 -> isiSoal = getString(R.string.soal_3)
            4 -> isiSoal = getString(R.string.soal_4)
            5 -> isiSoal = getString(R.string.soal_5)
            6 -> isiSoal = getString(R.string.soal_6)
            7 -> isiSoal = getString(R.string.soal_7)
            8 -> isiSoal = getString(R.string.soal_8)
            9 -> isiSoal = getString(R.string.soal_9)
            10 -> isiSoal = getString(R.string.soal_10)
            11 -> isiSoal = getString(R.string.soal_11)
            12 -> isiSoal = getString(R.string.soal_12)
            13 -> isiSoal = getString(R.string.soal_13)
            14 -> isiSoal = getString(R.string.soal_14)
            15 -> isiSoal = getString(R.string.soal_15)
            16 -> isiSoal = getString(R.string.soal_16)
            17 -> isiSoal = getString(R.string.soal_17)
            18 -> isiSoal = getString(R.string.soal_18)
            19 -> isiSoal = getString(R.string.soal_19)
            20 -> isiSoal = getString(R.string.soal_20)
            99 -> isiSoal = "Soal belum diakses"
        }
        return isiSoal
    }

    //TODO:SHOW THE RIGHT ANSWER
    override fun onBackPressed() {
        startActivity(Intent(this, LatihanActivity::class.java))
        finish()
    }
}