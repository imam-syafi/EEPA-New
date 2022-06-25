package com.dhandev.eepa.asah

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
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
            soalPertama.text = "1. "+ soal(0) +"\nKunci jawaban: ${jawabanBenar(0)}"
            soalKedua.text = "2. "+ soal(1) +"\nKunci jawaban: ${jawabanBenar(1)}"
            soalKetiga.text = "3. "+ soal(2)+"\nKunci jawaban: ${jawabanBenar(2)}"
            soalKeempat.text = "4. "+ soal(3) +"\nKunci jawaban: ${jawabanBenar(3)}"
            soalKelima.text = "5. "+ soal(4) +"\nKunci jawaban: ${jawabanBenar(4)}"
            soalKeenam.text = "6. "+ soal(5) +"\nKunci jawaban: ${jawabanBenar(5)}"
            soalKetujuh.text = "7. "+ soal(6) +"\nKunci jawaban: ${jawabanBenar(6)}"
            soalKedelapan.text = "8. "+ soal(7)+"\nKunci jawaban: ${jawabanBenar(7)}"
            soalKesembilan.text = "9. "+ soal(8) +"\nKunci jawaban: ${jawabanBenar(8)}"
            soalKesepuluh.text = "10. "+ soal(9) +"\nKunci jawaban: ${jawabanBenar(9)}"
            soalKesebelas.text = "11. "+ soal(10) +"\nKunci jawaban: ${jawabanBenar(10)}"
            soalKeduabelas.text = "12. "+ soal(11) +"\nKunci jawaban: ${jawabanBenar(11)}"
            soalKetigabelas.text = "13. "+ soal(12) +"\nKunci jawaban: ${jawabanBenar(12)}"
            soalKeempatbelas.text = "14. "+ soal(13) +"\nKunci jawaban: ${jawabanBenar(13)}"
            soalKelimabelas.text = "15. "+ soal(14) +"\nKunci jawaban: ${jawabanBenar(14)}"
            soalKeenambelas.text = "16. "+ soal(15) +"\nKunci jawaban: ${jawabanBenar(15)}"
            soalKetujuhbelas.text = "17. "+ soal(16) +"\nKunci jawaban: ${jawabanBenar(16)}"
            soalKedelapanbelas.text = "18. "+ soal(17) +"\nKunci jawaban: ${jawabanBenar(17)}"
            soalKesembilanbelas.text = "19. "+ soal(18)+"\nKunci jawaban: ${jawabanBenar(18)}"
            soalKeduapuluh.text = "20. "+ soal(19) +"\nKunci jawaban: ${jawabanBenar(19)}"

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
            progressCircular.trackColor = Color.WHITE
            val animation : ObjectAnimator = ObjectAnimator.ofInt(progressCircular, "progress", 0, final.toInt())
            animation.setDuration(1000)
            animation.interpolator
            animation.start()
            when(final){
                in 76.0..100.0 -> {
                    skorTotal.setTextColor(Color.BLUE)
                    progressCircular.setIndicatorColor(Color.BLUE)
                    descHasil.text = "Kategori:\nSangat baik, pertahankan! 😍"
                }
                in 51.0..75.0 -> {
                    skorTotal.setTextColor(resources.getColor(R.color.right))
                    progressCircular.setIndicatorColor(resources.getColor(R.color.right))
                    descHasil.text = "Kategori:\nBaik, tingkatkan! ✨"
                }
                in 26.0..50.0 -> {
                    skorTotal.setTextColor(resources.getColor(R.color.orange))
                    progressCircular.setIndicatorColor(resources.getColor(R.color.orange))
                    descHasil.text = "Kategori:\nKurang, belajar lagi! 👍"
                }
                else -> {
                    skorTotal.setTextColor(Color.RED)
                    progressCircular.setIndicatorColor(Color.RED)
                    descHasil.text = "Kategori:\nSangat kurang, belajar lebih giat lagi ya! 👌"
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
            16 -> isiSoal = getString(R.string.soal_16)
            17 -> isiSoal = getString(R.string.soal_17)
            18 -> isiSoal = getString(R.string.soal_18)
            19 -> isiSoal = getString(R.string.soal_19)
            20 -> isiSoal = getString(R.string.soal_20)
            99 -> isiSoal = "Soal belum diakses"
        }
        return isiSoal
    }

    private fun jawabanBenar (nomor : Int) : CharSequence{
        val urutanSoal = intent.getIntegerArrayListExtra("urutanSoal")
        var kunciJawaban= "Ini soal"
        when(urutanSoal?.get(nomor)){
            1 -> kunciJawaban = "Partikel fermion dan boson"
            2 -> kunciJawaban = "Partikel medan"
            3 -> kunciJawaban = "Memiliki spin bilangan bulat"
            4 -> kunciJawaban = "Proton"
            5 -> kunciJawaban = "Muon"
            6 -> kunciJawaban = "Model Standard"
            7 -> kunciJawaban = "Partikel pertukaran untuk gaya kuat"
            8 -> kunciJawaban = "Teori Medan Kuantum"
            9 -> kunciJawaban = "Baryon dan meson"
            10 -> kunciJawaban = "Satu quark up dan dua quark down"
            11 -> kunciJawaban = "Tersusun atas sebuah partikel dan antipartikel"
            12 -> kunciJawaban = "Lepton"
            13 -> kunciJawaban = getString(R.string.E)
            14 -> kunciJawaban = "Pion"
            15 -> kunciJawaban = "Kuark dan lepton"
            16 -> kunciJawaban = "Up, down, bottom, top, strange dan charm"
            17 -> kunciJawaban = "Gluon, foton, dan boson Higgs"
            18 -> kunciJawaban = "Graviton"
            19 -> kunciJawaban = "Teori Penyatuan Besar"
            20 -> kunciJawaban = "Jangkauan gaya gravitasi sangat luas"
            99 -> kunciJawaban = "Soal belum diakses"
        }
        return kunciJawaban
    }

    //TODO:SHOW THE RIGHT ANSWER
    override fun onBackPressed() {
        startActivity(Intent(this, LatihanActivity::class.java))
        finish()
    }
}