package com.dhandev.eepa.asah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import android.widget.Toast
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.dhandev.eepa.R
import com.dhandev.eepa.addition.RandomUnrepeated
import com.dhandev.eepa.databinding.ActivityMulaiBenarSalahBinding
import dev.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlin.random.Random

class MulaiBenarSalahActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMulaiBenarSalahBinding
    lateinit var countdown_timer: CountDownTimer
    var listSoal = mutableListOf<String>()
    var jumlahSoalh = 1
    var time_in_milli_seconds = 0L
    val r = RandomUnrepeated(1,15)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMulaiBenarSalahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val time = 1 //in minute
        time_in_milli_seconds = time.toLong() *60000L
        startTimer(time_in_milli_seconds)

        binding.apply {
            arrowBack.setOnClickListener { onBackPressed() }
            lifecycleScope.launch {
                val soal1 = load("1") ?: resources.getString(R.string.default_1)
                val soal2 = load("2") ?: resources.getString(R.string.default_2)
                val soal3 = load("3") ?: resources.getString(R.string.default_3)
                val soal4 = load("4") ?: resources.getString(R.string.default_4)
                val soal5 = load("5") ?: resources.getString(R.string.default_5)
                val soal6 = load("6") ?: resources.getString(R.string.default_6)
                val soal7 = load("7") ?: resources.getString(R.string.default_7)
                val soal8 = load("8") ?: resources.getString(R.string.default_8)
                val soal9 = load("9") ?: resources.getString(R.string.default_9)
                val soal10 = load("10") ?: resources.getString(R.string.default_10)
                val soal11 = load("11") ?: resources.getString(R.string.default_11)
                val soal12 = load("12") ?: resources.getString(R.string.default_12)
                val soal13 = load("13") ?: resources.getString(R.string.default_13)
                val soal14 = load("14") ?: resources.getString(R.string.default_14)
                val soal15 = load("15") ?: resources.getString(R.string.default_15)

                listSoal.add(soal1)
                listSoal.add(soal2)
                listSoal.add(soal3)
                listSoal.add(soal4)
                listSoal.add(soal5)
                listSoal.add(soal6)
                listSoal.add(soal7)
                listSoal.add(soal8)
                listSoal.add(soal9)
                listSoal.add(soal10)
                listSoal.add(soal11)
                listSoal.add(soal12)
                listSoal.add(soal13)
                listSoal.add(soal14)
                listSoal.add(soal15)
                showSoal(r.nextInt())

                btnYes.setOnClickListener {
                    if (jumlahSoalh < 15){
                        showSoal(r.nextInt())
                        jumlahSoalh++
                        title.text = "Pernyataan ${jumlahSoalh}/15"
                    }
                    else {
                        Toast.makeText(this@MulaiBenarSalahActivity, "Sudah Sampai AKhir", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    //TODO:Default 15 soal, tapi pengguna bisa mengedit
    private fun showSoal(nomorSoal: Int) {
        binding.apply {
            when(nomorSoal){
                1 -> pernyataan.setText(listSoal[0])
                2 -> pernyataan.setText(listSoal[1])
                3 -> pernyataan.setText(listSoal[2])
                4 -> pernyataan.setText(listSoal[3])
                5 -> pernyataan.setText(listSoal[4])
                6 -> pernyataan.setText(listSoal[5])
                7 -> pernyataan.setText(listSoal[6])
                8 -> pernyataan.setText(listSoal[7])
                9 -> pernyataan.setText(listSoal[8])
                10 -> pernyataan.setText(listSoal[9])
                11 -> pernyataan.setText(listSoal[10])
                12 -> pernyataan.setText(listSoal[11])
                13 -> pernyataan.setText(listSoal[12])
                14 -> pernyataan.setText(listSoal[13])
                15 -> pernyataan.setText(listSoal[14])
                else -> pernyataan.setText("Gagal")
            }
        }
    }

    private fun startTimer(timeInMilliSeconds: Long) {
        countdown_timer = object : CountDownTimer(timeInMilliSeconds, 1000){
            override fun onTick(p0: Long) {
                time_in_milli_seconds = p0
                updateTextUI()
            }

            override fun onFinish() {
//                val intent = Intent(this@MulaiBenarSalahActivity, HasilLatihanActivity::class.java)
//                startActivity(intent)
//                finish()
                Toast.makeText(this@MulaiBenarSalahActivity, "Waktu Habis!", Toast.LENGTH_SHORT).show()
            }
        }
        countdown_timer.start()
    }

    private fun updateTextUI() {
        val minute = (time_in_milli_seconds / 1000) / 60
        val seconds = (time_in_milli_seconds / 1000) % 60

        binding.timer.text = "$minute:$seconds"
    }

    suspend private fun load(key : String) : String? {
        val dataStoreKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }

    override fun onBackPressed() {
        val BottomSheetDialog = BottomSheetMaterialDialog.Builder(this)
            .setTitle("Akhiri Latihan?")
            .setMessage("Jawaban Anda tidak tersimpan, yakin keluar dari latihan?")
            .setCancelable(true)
            .setPositiveButton("Akhiri", R.drawable.ic_baseline_done_24) { dialog, which ->
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