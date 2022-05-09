package com.dhandev.eepa.asah

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.dhandev.eepa.R
import com.dhandev.eepa.addition.RandomUnrepeated
import com.dhandev.eepa.databinding.ActivityMulaiBenarSalahBinding
import dev.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog
import dev.shreyaspatil.MaterialDialog.MaterialDialog
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MulaiBenarSalahActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMulaiBenarSalahBinding
    private lateinit var sharedPred : SharedPreferences
    lateinit var countdown_timer: CountDownTimer
    var listSoal = mutableListOf<String>()
    var jumlahSoalh = 1
    var time_in_milli_seconds = 0L
    val r = RandomUnrepeated(1,15)
    var jawaban = "0"   //default "0" = salah, jika bernilai "1" berarti benar
    var skor = 0

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

                val colorMatrix = ColorMatrix()
                colorMatrix.setSaturation(0f)
                val filter = ColorMatrixColorFilter(colorMatrix)
                btnYes.setColorFilter(filter)
                btnNo.setColorFilter(filter)

                //TODO: ATUR AGAR BTN YES NO BISA DISESUAIKAN DENGAN KUNCI JAWABAN
                btnYes.setOnClickListener {
                    btnYes.clearColorFilter()
                    btnNo.visibility = View.GONE
                    btnNext.visibility = View.VISIBLE
                    solusi.visibility = View.VISIBLE
                    when{
                        jawaban == "0" -> {
                            solusi.setText("Jawaban Anda: Benar\nKunci: Pernyataan ini benar")
                            skor++
                        }
                        jawaban == "1" -> solusi.setText("Jawaban Anda: Benar\nKunci: Pernyataan ini salah")
                    }
                    btnYes.isClickable = false
                }
                btnNo.setOnClickListener {
                    btnNo.clearColorFilter()
                    btnYes.visibility = View.GONE
                    btnNext.visibility = View.VISIBLE
                    solusi.visibility = View.VISIBLE
                    when{
                        jawaban == "0" -> solusi.setText("Jawaban Anda: Salah\nKunci: Pernyataan ini benar")
                        jawaban == "1" -> {
                            solusi.setText("Jawaban Anda: Salah\nKunci: Pernyataan ini salah")
                            skor++
                        }
                    }
                    btnNo.isClickable = false
                }
                btnNext.setOnClickListener {
                    btnYes.setColorFilter(filter)
                    btnNo.setColorFilter(filter)
                    btnYes.visibility = View.VISIBLE
                    btnNo.visibility = View.VISIBLE
                    solusi.setText("")
                    btnNext.visibility = View.GONE
                    solusi.visibility = View.GONE
                    btnYes.isClickable = true
                    btnNo.isClickable = true

                    if (jumlahSoalh < 15){
                        showSoal(r.nextInt())
                        jumlahSoalh++
                        title.text = "Pernyataan ${jumlahSoalh}/15"
                    }
                    else {
                        countdown_timer.cancel()
                        result()
                    }
                }
            }
        }
    }

    private fun result() {
        sharedPred = this.getSharedPreferences("User", MODE_PRIVATE)
        val username : String?  = sharedPred.getString("userName", null)
        val ResultDialog = MaterialDialog.Builder(this)
            .setTitle(getString(R.string.skorAkhir, skor, skor))
            .setMessage("Selamat $username, berikut skor Anda dalam latihan benar salah")
            .setPositiveButton("Keluar", R.drawable.ic_baseline_done_24) { dialog, which ->
                lifecycleScope.launch {save("NilaiBS", skor.toString()) }
                startActivity(Intent(this, BenarSalahActivity::class.java))
                finish()}
            .setNegativeButton("Ulang", R.drawable.ic_baseline_refresh_24) { dialog, which ->
                dialog.dismiss()
                recreate() }
            .setAnimation("done.json")
            .build()
        ResultDialog.show()
        ResultDialog.animationView.scaleType = ImageView.ScaleType.CENTER_INSIDE
    }

    private fun showSoal(nomorSoal: Int) {
        binding.apply {
            when(nomorSoal){
                1 -> lifecycleScope.launch{
                    pernyataan.setText(listSoal[0])
                    jawaban = load("j1") ?: "0"
                }
                2 -> lifecycleScope.launch{
                    pernyataan.setText(listSoal[1])
                    jawaban = load("j2") ?: "0" //default jawaban sesuaikan dengan soal
                }
                3 -> lifecycleScope.launch{
                    pernyataan.setText(listSoal[2])
                    jawaban = load("j3") ?: "0"
                }
                4 -> lifecycleScope.launch{
                    pernyataan.setText(listSoal[3])
                    jawaban = load("j4") ?: "0"
                }
                5 -> lifecycleScope.launch{
                    pernyataan.setText(listSoal[4])
                    jawaban = load("j5") ?: "0"
                }
                6 -> lifecycleScope.launch{
                    pernyataan.setText(listSoal[5])
                    jawaban = load("j6") ?: "0"
                }
                7 -> lifecycleScope.launch{
                    pernyataan.setText(listSoal[6])
                    jawaban = load("j7") ?: "0"
                }
                8 -> lifecycleScope.launch{
                    pernyataan.setText(listSoal[7])
                    jawaban = load("j8") ?: "0"
                }
                9 -> lifecycleScope.launch{
                    pernyataan.setText(listSoal[8])
                    jawaban = load("j9") ?: "0"
                }
                10 -> lifecycleScope.launch{
                    pernyataan.setText(listSoal[9])
                    jawaban = load("j10") ?: "0"
                }
                11 -> lifecycleScope.launch{
                    pernyataan.setText(listSoal[10])
                    jawaban = load("j11") ?: "0"
                }
                12 -> lifecycleScope.launch{
                    pernyataan.setText(listSoal[11])
                    jawaban = load("j12") ?: "0"
                }
                13 -> lifecycleScope.launch{
                    pernyataan.setText(listSoal[12])
                    jawaban = load("j13") ?: "0"
                }
                14 -> lifecycleScope.launch{
                    pernyataan.setText(listSoal[13])
                    jawaban = load("j14") ?: "0"
                }
                15 -> lifecycleScope.launch{
                    pernyataan.setText(listSoal[14])
                    jawaban = load("j15") ?: "0"
                }
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
                result()
            }
        }
        countdown_timer.start()
    }

    private fun updateTextUI() {
        val minute = (time_in_milli_seconds / 1000) / 60
        val seconds = (time_in_milli_seconds / 1000) % 60

        binding.timer.text = "$minute:$seconds"
    }

    suspend private fun save(key : String, value : String) {
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit { list ->
            list[dataStoreKey] = value
        }
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
                startActivity(Intent(this, BenarSalahActivity::class.java))
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