package com.dhandev.eepa.materi

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityMateriSubatomikBinding
import com.skydoves.balloon.*
import com.skydoves.balloon.overlay.BalloonOverlayRoundRect


class MateriSubatomik : AppCompatActivity() {

    private lateinit var binding: ActivityMateriSubatomikBinding
    private lateinit var sharedPred : SharedPreferences
    private lateinit var sharedPredLastRead : SharedPreferences
    private var pedomanSetText = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materi_subatomik)
        binding = ActivityMateriSubatomikBinding.inflate(layoutInflater)
        sharedPred = this.getSharedPreferences("Tampilan", MODE_PRIVATE)
        sharedPredLastRead = this.getSharedPreferences("User", MODE_PRIVATE)
        loadUkuranbaru()
        loadLatarBaru()

        val editLastRead = sharedPredLastRead.edit()
        editLastRead.putInt("subMateri", 1)
        editLastRead.apply()

        val highlight = intent.getStringExtra("highlight")
        if (highlight != null) {
            Toast.makeText(this, highlight, Toast.LENGTH_SHORT).show()
            setHighLightedText(binding.mSubatomik1, highlight)
        }

        pedomanSetText = sharedPredLastRead.getInt("pedomanSetText", 0)
        if (pedomanSetText==0){
            newSpotlight()
        }

        with(binding){
            setContentView(root)
            aturTeks.visibility = View.GONE

            binding.arrowBack.setOnClickListener {
                onBackPressed()
            }

            setText.setOnClickListener {
                if (aturTeks.isVisible){
                    aturTeks.visibility = View.GONE
                } else {
                    aturTeks.visibility = View.VISIBLE
                }
            }

            toggleGroup.addOnButtonCheckedListener{toggleGroup, checkedId, isChecked ->
                if (isChecked){
                    when(checkedId){
                        R.id.btnSmall -> gantiUkuran(R.style.FontParagrafSmall, toggleGroup.checkedButtonId)
                        R.id.btnMedium -> gantiUkuran(R.style.FontParagraf, toggleGroup.checkedButtonId)
                        R.id.btnLarge -> gantiUkuran(R.style.FontParagrafLarge, toggleGroup.checkedButtonId)
                    }
                } else {
                    if (toggleGroup.checkedButtonId == View.NO_ID){
                        gantiUkuran(R.style.FontParagraf, toggleGroup.checkedButtonId)
                    }
                }

            }

            toggleGroupColor.addOnButtonCheckedListener{toggleGroup, checkedId, isChecked ->
                if (isChecked){
                    when(checkedId){
                        R.id.btnGreen -> gantiLatar(R.color.greenRead, toggleGroupColor.checkedButtonId)
                        R.id.btnPeach -> gantiLatar(R.color.peachRead, toggleGroupColor.checkedButtonId)
                        R.id.btnOrange -> gantiLatar(R.color.orangeRead, toggleGroupColor.checkedButtonId)
                    }
                } else {
                    if (toggleGroup.checkedButtonId == View.NO_ID){
                        gantiLatar(R.color.white, toggleGroupColor.checkedButtonId)
                    }
                }
            }

            btnNext.setOnClickListener {
                startActivity(Intent(this@MateriSubatomik, MateriTMQ::class.java))
            }
        }
    }

    private fun gantiLatar(greenRead: Int, pressed: Int) {
        val latarBaru: Int = greenRead
        val tombol: Int = pressed
        val Editor: SharedPreferences.Editor = sharedPred.edit()
        Editor.putInt("gantiLatar", latarBaru)
        Editor.putInt("tombolTerpilih", tombol)
        Editor.apply()
        loadLatarBaru()
    }

    private fun loadLatarBaru() {
        val sharedLatarId = sharedPred.getInt("gantiLatar", 0)
        val sharedTombolId = sharedPred.getInt("tombolTerpilih", 3)
        if (sharedLatarId.equals(0) && sharedTombolId.equals(3)){
            binding.latarSubatomik.setBackgroundColor(getColor(R.color.white))
        } else {
            binding.latarSubatomik.setBackgroundColor(getColor(sharedLatarId))
            binding.toggleGroupColor.check(sharedTombolId)
        }
    }

    private fun gantiUkuran(fontParagrafSmall: Int, checkedButtonId: Int) {
        val ukuranBaru: Int = fontParagrafSmall
        val tombolUkuran : Int = checkedButtonId
        val Editor: SharedPreferences.Editor = sharedPred.edit()
        Editor.putInt("ukuranBaru", ukuranBaru)
        Editor.putInt("tombolUkuranTerpilih", tombolUkuran)
        Editor.apply()
        loadUkuranbaru()
    }

    private fun loadUkuranbaru(){
        val sharedUkuranId = sharedPred.getInt("ukuranBaru", 0)
        val sharedTombolUkuranId = sharedPred.getInt("tombolUkuranTerpilih", 3)
        if (sharedUkuranId.equals(0)){
            binding.body1.setTextAppearance(R.style.FontParagraf)
            binding.mSubatomik1.setTextAppearance(R.style.FontParagraf)
            binding.mSubatomik12.setTextAppearance(R.style.FontParagraf)
            binding.mSubatomik2.setTextAppearance(R.style.FontParagraf)
            binding.mSubatomik22.setTextAppearance(R.style.FontParagraf)
            binding.mSubatomik3.setTextAppearance(R.style.FontParagraf)
            binding.mSubatomik32.setTextAppearance(R.style.FontParagraf)
        } else {
            binding.body1.setTextAppearance(sharedUkuranId)
            binding.mSubatomik1.setTextAppearance(sharedUkuranId)
            binding.mSubatomik12.setTextAppearance(sharedUkuranId)
            binding.mSubatomik2.setTextAppearance(sharedUkuranId)
            binding.mSubatomik22.setTextAppearance(sharedUkuranId)
            binding.mSubatomik3.setTextAppearance(sharedUkuranId)
            binding.mSubatomik32.setTextAppearance(sharedUkuranId)
            binding.toggleGroup.check(sharedTombolUkuranId)
        }
    }

    fun setHighLightedText(tv: TextView, textToHighlight: String) {
        val tvt = tv.text.toString()
        var ofe = tvt.indexOf(textToHighlight, 0)
        val wordToSpan: Spannable = SpannableString(tv.text)
        var ofs = 0
        while (ofs < tvt.length && ofe != -1) {
            ofe = tvt.indexOf(textToHighlight, ofs)
            if (ofe == -1) break else {
                // set color here
                wordToSpan.setSpan(
                    BackgroundColorSpan(-0x100),
                    ofe,
                    ofe + textToHighlight.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                tv.setText(wordToSpan, TextView.BufferType.SPANNABLE)
            }
            ofs = ofe + 1
        }
    }

    private fun newSpotlight() {
        pedomanSetText++
        val Editor:SharedPreferences.Editor = sharedPredLastRead.edit()
        Editor.putInt("pedomanSetText", pedomanSetText).apply()
        binding.apply {
            val balloon = Balloon.Builder(this@MateriSubatomik)
                .setWidth(BalloonSizeSpec.WRAP)
                .setHeight(BalloonSizeSpec.WRAP)
                .setText("Ganti ukuran teks dan warna latar")
                .setArrowPositionRules(ArrowPositionRules.ALIGN_BALLOON)
                .setTextColorResource(R.color.white)
                .setTextSize(15f)
                .setIconDrawableResource(R.drawable.ic_baseline_info_24)
                .setPadding(10)
                .setCornerRadius(20f)
                .setBackgroundColorResource(R.color.greenBright)
                .setBalloonAnimation(BalloonAnimation.ELASTIC)
                .setIsVisibleOverlay(true)
                .setOverlayShape(BalloonOverlayRoundRect(20f, 20f))
                .setOverlayColorResource(R.color.transparent)
                .setLifecycleOwner(this@MateriSubatomik)
                .setBalloonHighlightAnimation(BalloonHighlightAnimation.SHAKE)
                .build()

            setText.showAlignBottom(balloon)
        }
    }

}