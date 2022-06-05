package com.dhandev.eepa.asah

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
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
    private lateinit var sharedPred : SharedPreferences

    var mutableList = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
    var listHasil = mutableListOf<Int>()
    var listJawaban = mutableListOf<Int>()
    var skor = 0
    var nomorSoal = 2
    var soal = Random.nextInt(1,16)
    var answer = 0
    lateinit var countdown_timer: CountDownTimer
    var time_in_milli_seconds = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMulaiLatihanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        randomize(soal)
        showSoal(soal)

        sharedPred = this.getSharedPreferences("User", MODE_PRIVATE)

        val time = sharedPred.getString("terpilih", "15") //in minute
        val tepilih = time?.toInt()
        if (tepilih != null) {
            time_in_milli_seconds = tepilih.toLong() *60000L
        }
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
            randomize(Random.nextInt(1,16))
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
                11 -> SoalKesebelas()
                12 -> SoalKeduabelas()
                13 -> SoalKetigabelas()
                14 -> SoalKeempatbelas()
                15 -> SoalKelimabelas()

            }
        }
    }

    private fun SoalKelimabelas() {
        binding.apply {
            soalLatihan.text = getString(R.string.soal_15)
            opsi1.text = "Hanya mampu menjelaskan gaya gravitasi"
            opsi2.text = "Belum mampu menjelaskan gaya gravitasi"
            opsi3.text = "Boson Higgs belum terbukti secara eksperimen"
            opsi4.text = "Hanya teori saja, tanpa ada bukti eksperimen"

            listHasil.add(15)
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

    private fun SoalKeempatbelas() {
        binding.apply {
            soalLatihan.text = getString(R.string.soal_14)
            opsi1.text = "Up, Down, Bottom, Top, Strange dan Charm"
            opsi2.text = "Elektron, Elektron Neutrino, Muon, Muon Neutrino, Tau dan Tau Neutrino"
            opsi3.text = "Up, Down, Bottom, Elektron, Muon, dan Tau"
            opsi4.text = "Proton, Neutron, Pion, dan Kaon"

            listHasil.add(14)
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

    private fun SoalKetigabelas() {
        binding.apply {
            soalLatihan.text = getString(R.string.soal_13)
            opsi1.text = "Boson W⁺ dan W⁻"
            opsi2.text = "Boson Z⁰"
            opsi3.text = "Boson Higgs"
            opsi4.text = "Gluon"

            listHasil.add(13)
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

    private fun SoalKeduabelas() {
        binding.apply {
            soalLatihan.text = getString(R.string.soal_12)
            opsi1.text = "Hadron"
            opsi2.text = "Lepton"
            opsi3.text = "Baryon"
            opsi4.text = "Meson"

            listHasil.add(12)
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

    private fun SoalKesebelas() {
        binding.apply {
            soalLatihan.text = getString(R.string.soal_11)
            opsi1.text = "Elektron dan Antielektron"
            opsi2.text = "Muon dan Tau Neutrino"
            opsi3.text = "Elektron Neutrino dan Muon Neutrino"
            opsi4.text = "Antimuon dan Antielektron"

            listHasil.add(11)
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

    private fun SoalKesepuluh() {
        binding.apply {
            soalLatihan.text = getString(R.string.soal_10)
            opsi1.text = "Tersusun dari tiga kuark"
            opsi2.text = "Tersusun dari sebuah partikel dan antipartikel"
            opsi3.text = "Tidak tersusun dari partikel lain"
            opsi4.text = "Tidak mengalami gaya kuat"

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
            soalLatihan.text = getString(R.string.soal_9)
            opsi1.text = "Baryon"
            opsi2.text = "Meson"
            opsi3.text = "Lepton"
            opsi4.text = "Partikel Bebas"

            listHasil.add(9)
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

    private fun SoalKedelapan() {
        binding.apply {
            soalLatihan.text = getString(R.string.soal_8)
            opsi1.text = "Partikel pembawa gaya elektromagnetik"
            opsi2.text = "Partikel pembawa gaya lemah"
            opsi3.text = "Partikel pembawa gaya kuat"
            opsi4.text = "Partikel pembawa gaya gravitasi"

            listHasil.add(8)
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

    private fun SoalKetujuh() {
        binding.apply {
            soalLatihan.text = getString(R.string.soal_7)
            opsi1.text = "Kromodinamika Kuantum"
            opsi2.text = "Teori Tentang Segalanya"
            opsi3.text = "Model Standar"
            opsi4.text = "Teori Penyatuan Besar"

            listHasil.add(7)
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

    private fun SoalKeenam() {
        binding.apply {
            soalLatihan.text = getString(R.string.soal_6)
            opsi1.text = "Partikel medan"
            opsi2.text = "Partikel Fermion"
            opsi3.text = "Partikel Boson"
            opsi4.text = "Partikel Materi"

            listHasil.add(6)
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

    private fun SoalKelima() {
        binding.apply {
            soalLatihan.text = getString(R.string.soal_5)
            opsi1.text = "Tabung sinar katoda"
            opsi2.text = "Gold Foil Experiment"
            opsi3.text = "Ruang Kabut"
            opsi4.text = "LHC di CERN"

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
            soalLatihan.text = getString(R.string.soal_4)
            opsi1.text = "Elektron"
            opsi2.text = "Proton"
            opsi3.text = "Neutron"
            opsi4.text = "Positron"

            listHasil.add(4)
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

    private fun SoalKetiga() {
        binding.apply {
            soalLatihan.text = getString(R.string.soal_3)
            opsi1.text = "Memiliki spin setengah bilangan bulat"
            opsi2.text = "Memiliki spin bilangan bulat"
            opsi3.text = "Tersusun dari partikel yang lebih sederhana"
            opsi4.text = "Tidak tersusun dari partikel lain"

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
            soalLatihan.text = getString(R.string.soal_2)
            opsi1.text = "Partikel elementer dan komposit"
            opsi2.text = "Partikel fermion dan boson"
            opsi3.text = "Partikel elementer dan fermion"
            opsi4.text = "Partikel komposit dan boson"

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
            soalLatihan.text = getString(R.string.soal_1)
            opsi1.text = "Partikel elementer dan komposit"
            opsi2.text = "Partikel fermion dan boson"
            opsi3.text = "Partikel elementer dan fermion"
            opsi4.text = "Partikel komposit dan boson"

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
            if (nomorSoal < 15){
                soal = Random.nextInt(1,16)
                randomize(soal)
                title.text = "Nomor ${nomorSoal++}/15"
//                Toast.makeText(this@MulaiLatihanActivity, listJawaban.toString(), Toast.LENGTH_SHORT).show()
            } else if (nomorSoal == 15) {
                soal = Random.nextInt(1,16)
                nomorSoal++
                randomize(soal)
                title.text = "Nomor 15/15"
                btnNext.text = "Kumpulkan"
                btnNext.backgroundTintList = ContextCompat.getColorStateList(this@MulaiLatihanActivity, R.color.right)

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
                startActivity(Intent(this, LatihanActivity::class.java))
                finish()}
            .setNegativeButton("Batal", R.drawable.ic_baseline_close_24) { dialog, which ->
                dialog.dismiss()
            }
            .setAnimation("question.json")
            .build()
        BottomSheetDialog.show()
        BottomSheetDialog.animationView.scaleType = ImageView.ScaleType.CENTER_INSIDE
    }
}