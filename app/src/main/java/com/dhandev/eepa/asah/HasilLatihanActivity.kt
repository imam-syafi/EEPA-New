package com.dhandev.eepa.asah

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.airbnb.paris.extensions.style
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityHasilLatihanBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.text.DecimalFormat


class HasilLatihanActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHasilLatihanBinding
    private lateinit var sharedPred : SharedPreferences


    //TODO: Share Result Button
    //option 1 = screenShot mode
    //option 2 = custom report, lengkap dengan nama, skor, dan detail jawaban
    
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
            val user = Firebase.auth.currentUser
            val username = user?.displayName

            val urutanSoal = intent.getStringArrayListExtra("urutanJawabanUser")
            soalPertama.text = "1. "+ soal(0) +"\nJawaban: ${jawabanUser(0)}" +"\nKunci jawaban: ${jawabanBenar(0)}"
            soalKedua.text = "2. "+ soal(1) +"\nJawaban: ${jawabanUser(1)}" +"\nKunci jawaban: ${jawabanBenar(1)}"
            soalKetiga.text = "3. "+ soal(2) +"\nJawaban: ${jawabanUser(2)}"+"\nKunci jawaban: ${jawabanBenar(2)}"
            soalKeempat.text = "4. "+ soal(3) +"\nJawaban: ${jawabanUser(3)}" +"\nKunci jawaban: ${jawabanBenar(3)}"
            soalKelima.text = "5. "+ soal(4) +"\nJawaban: ${jawabanUser(4)}" +"\nKunci jawaban: ${jawabanBenar(4)}"
            soalKeenam.text = "6. "+ soal(5) +"\nJawaban: ${jawabanUser(5)}" +"\nKunci jawaban: ${jawabanBenar(5)}"
            soalKetujuh.text = "7. "+ soal(6) +"\nJawaban: ${jawabanUser(6)}" +"\nKunci jawaban: ${jawabanBenar(6)}"
            soalKedelapan.text = "8. "+ soal(7) +"\nJawaban: ${jawabanUser(7)}"+"\nKunci jawaban: ${jawabanBenar(7)}"
            soalKesembilan.text = "9. "+ soal(8) +"\nJawaban: ${jawabanUser(8)}" +"\nKunci jawaban: ${jawabanBenar(8)}"
            soalKesepuluh.text = "10. "+ soal(9) +"\nJawaban: ${jawabanUser(9)}" +"\nKunci jawaban: ${jawabanBenar(9)}"
            soalKesebelas.text = "11. "+ soal(10) +"\nJawaban: ${jawabanUser(10)}" +"\nKunci jawaban: ${jawabanBenar(10)}"
            soalKeduabelas.text = "12. "+ soal(11) +"\nJawaban: ${jawabanUser(11)}" +"\nKunci jawaban: ${jawabanBenar(11)}"
            soalKetigabelas.text = "13. "+ soal(12) +"\nJawaban: ${jawabanUser(12)}" +"\nKunci jawaban: ${jawabanBenar(12)}"
            soalKeempatbelas.text = "14. "+ soal(13) +"\nJawaban: ${jawabanUser(13)}" +"\nKunci jawaban: ${jawabanBenar(13)}"
            soalKelimabelas.text = "15. "+ soal(14) +"\nJawaban: ${jawabanUser(14)}" +"\nKunci jawaban: ${jawabanBenar(14)}"

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

            fireworks.setAnimation("fireworks.json")
            fireworks.playAnimation()

            val final : Double = (skor.toDouble()/150.0)*100.0
            val df : DecimalFormat = DecimalFormat("#.#")
            skorTotal.text = df.format(final).toString()
            progressCircular.trackColor = Color.WHITE
            val animation : ObjectAnimator = ObjectAnimator.ofInt(progressCircular, "progress", 0, final.toInt())
            animation.setDuration(1000)
            animation.interpolator
            animation.start()
            when(final){
                in 76.0..100.0 -> {
                    skorTotal.setTextColor(resources.getColor(R.color.greenBright))
                    tvskor.setTextColor(resources.getColor(R.color.greenBright))
                    progressCircular.setIndicatorColor(resources.getColor(R.color.greenBright))
                    descHasil.text = "Sangat baik"
                    keterangan.text = "$username, pertahankan ya! 👍"
                }
                in 51.0..75.9 -> {
                    skorTotal.setTextColor(resources.getColor(R.color.yellow))
                    tvskor.setTextColor(resources.getColor(R.color.yellow))
                    progressCircular.setIndicatorColor(resources.getColor(R.color.yellow))
                    descHasil.text = "Baik"
                    keterangan.text = "$username, tingkatkan terus! ✨"
                }
                in 26.0..50.9 -> {
                    skorTotal.setTextColor(resources.getColor(R.color.orange))
                    tvskor.setTextColor(resources.getColor(R.color.orange))
                    progressCircular.setIndicatorColor(resources.getColor(R.color.orange))
                    descHasil.text = "Kurang"
                    keterangan.text = "$username, belajar lagi! 👌"
                }
                else -> {
                    skorTotal.setTextColor(resources.getColor(R.color.wrong))
                    tvskor.setTextColor(resources.getColor(R.color.wrong))
                    progressCircular.setIndicatorColor(resources.getColor(R.color.wrong))
                    descHasil.text = "Sangat kurang"
                    keterangan.text = "$username, belajar lebih giat lagi ya! 💪"
                }
            }

            val Editor:SharedPreferences.Editor = sharedPred.edit()
            Editor.putInt("latestScore", skor)
            Editor.apply()

            rincian.setOnClickListener {
                if (rincianHasil.isVisible){
                    rincianHasil.visibility = View.GONE
                } else {
                    rincianHasil.visibility = View.VISIBLE
                    scrollToViewTop(scroll, rincian)
                }
            }
        }
    }
    private fun scrollToViewTop(scrollView: ScrollView, childView: View) {
        val delay: Long = 100 //delay to let finish with possible modifications to ScrollView
        scrollView.postDelayed({ scrollView.smoothScrollBy(0, childView.top) }, delay)
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
            99 -> isiSoal = "Soal belum diakses"
        }
        return isiSoal
    }

    private fun jawabanBenar (nomor : Int) : CharSequence{
        val urutanSoal = intent.getIntegerArrayListExtra("urutanSoal")
        var kunciJawaban= "Ini soal"
        when(urutanSoal?.get(nomor)){
            1 -> kunciJawaban = "Partikel medan"
            2 -> kunciJawaban = "Memiliki spin bilangan bulat"
            3 -> kunciJawaban = "Muon"
            4 -> kunciJawaban = "Model Standard"
            5 -> kunciJawaban = "Partikel pertukaran untuk gaya kuat"
            6 -> kunciJawaban = "Teori Medan Kuantum"
            7 -> kunciJawaban = "Baryon dan meson"
            8 -> kunciJawaban = "Satu quark up dan dua quark down"
            9 -> kunciJawaban = "Lepton"
            10 -> kunciJawaban = getString(R.string.E)
            11 -> kunciJawaban = "Pion"
            12 -> kunciJawaban =  "Up, down, bottom, top, strange dan charm"
            13 -> kunciJawaban = "Gluon, foton, dan boson Higgs"
            14 -> kunciJawaban = "Graviton"
            15 -> kunciJawaban =  "Teori Penyatuan Besar"
            99 -> kunciJawaban = "Soal belum diakses"
        }
        return kunciJawaban
    }

    private fun jawabanUser (nomor : Int) : CharSequence{
        val urutanSoal = intent.getIntegerArrayListExtra("urutanSoal")
        val urutanJawaban = intent.getStringArrayListExtra("urutanJawabanUser")
        var jawabanPengguna= "Ini soal"
        when(urutanSoal?.get(nomor)){
            1 -> jawabanPengguna = "${urutanJawaban?.get(nomor)}"
            2 -> jawabanPengguna = "${urutanJawaban?.get(nomor)}"
            3 -> jawabanPengguna = "${urutanJawaban?.get(nomor)}"
            4 -> jawabanPengguna = "${urutanJawaban?.get(nomor)}"
            5 -> jawabanPengguna = "${urutanJawaban?.get(nomor)}"
            6 -> jawabanPengguna = "${urutanJawaban?.get(nomor)}"
            7 -> jawabanPengguna = "${urutanJawaban?.get(nomor)}"
            8 -> jawabanPengguna = "${urutanJawaban?.get(nomor)}"
            9 -> jawabanPengguna = "${urutanJawaban?.get(nomor)}"
            10 -> jawabanPengguna = "${urutanJawaban?.get(nomor)}"
            11 -> jawabanPengguna = "${urutanJawaban?.get(nomor)}"
            12 -> jawabanPengguna =  "${urutanJawaban?.get(nomor)}"
            13 -> jawabanPengguna = "${urutanJawaban?.get(nomor)}"
            14 -> jawabanPengguna = "${urutanJawaban?.get(nomor)}"
            15 -> jawabanPengguna =  "${urutanJawaban?.get(nomor)}"
            99 -> jawabanPengguna = "Soal belum diakses"
        }
        return jawabanPengguna
    }


    override fun onBackPressed() {
        startActivity(Intent(this, LatihanActivity::class.java))
        finish()
    }
}