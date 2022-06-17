package com.dhandev.eepa.materi

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.Target
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityMateriStandarModelBinding
import com.dhandev.eepa.helper.glideImage
import com.dhandev.eepa.ui.imageViewer.ImageViewerMateriActivity

class MateriStandarModel : AppCompatActivity() {
    private lateinit var binding : ActivityMateriStandarModelBinding
    private lateinit var sharedPred: SharedPreferences
    private lateinit var sharedPredLastRead : SharedPreferences
    var URL = "https://docs.google.com/uc?id=1S3frX3nmvgm7-YPEfRpxZDynPUOWWmTd"
    var URL2 = "https://docs.google.com/uc?id=1S4FN0RlnfKnDs5uDr2glop9pnA2dqgYD"
    var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMateriStandarModelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPred = this.getSharedPreferences("Tampilan", MODE_PRIVATE)
        sharedPredLastRead = this.getSharedPreferences("User", MODE_PRIVATE)
        loadUkuranbaru()
        loadLatarBaru()

        val editLastRead = sharedPredLastRead.edit()
        editLastRead.putInt("subMateri", 5)
        editLastRead.apply()

        binding.apply {
            setContentView(root)
            aturTeks.visibility = View.GONE

            arrowBack.setOnClickListener {
                onBackPressed()
            }

            setText.setOnClickListener {
                if (aturTeks.isVisible) {
                    aturTeks.visibility = View.GONE
                } else {
                    aturTeks.visibility = View.VISIBLE
                }
            }

            toggleGroup.addOnButtonCheckedListener { toggleGroup, checkedId, isChecked ->
                if (isChecked) {
                    when (checkedId) {
                        R.id.btnSmall -> gantiUkuran(
                            R.style.FontParagrafSmall,
                            toggleGroup.checkedButtonId
                        )
                        R.id.btnMedium -> gantiUkuran(
                            R.style.FontParagraf,
                            toggleGroup.checkedButtonId
                        )
                        R.id.btnLarge -> gantiUkuran(
                            R.style.FontParagrafLarge,
                            toggleGroup.checkedButtonId
                        )
                    }
                } else {
                    if (toggleGroup.checkedButtonId == View.NO_ID) {
                        gantiUkuran(R.style.FontParagraf, toggleGroup.checkedButtonId)
                    }
                }
            }

            toggleGroupColor.addOnButtonCheckedListener { toggleGroup, checkedId, isChecked ->
                if (isChecked) {
                    when (checkedId) {
                        R.id.btnGreen -> gantiLatar(
                            R.color.greenRead,
                            toggleGroupColor.checkedButtonId
                        )
                        R.id.btnPeach -> gantiLatar(
                            R.color.peachRead,
                            toggleGroupColor.checkedButtonId
                        )
                        R.id.btnOrange -> gantiLatar(
                            R.color.orangeRead,
                            toggleGroupColor.checkedButtonId
                        )
                    }
                } else {
                    if (toggleGroup.checkedButtonId == View.NO_ID) {
                        gantiLatar(R.color.white, toggleGroupColor.checkedButtonId)
                    }
                }
            }

            val desc = tvCaption1.text.toString()
            val desc2 = tvCaption2.text.toString()
            glideImage.load(this@MateriStandarModel, URL, gambar)
            glideImage.load(this@MateriStandarModel, URL2, gambar2)
            gambar.setOnClickListener {
                openImageViewer(URL, desc)
            }
            gambar2.setOnClickListener {
                openImageViewer(URL2, desc2)
            }

            btnNext.visibility = View.VISIBLE
            btnNext.setOnClickListener {
                startActivity(Intent(this@MateriStandarModel, MateriTerkini::class.java))
            }

            btnPrev.setOnClickListener {
                startActivity(Intent(this@MateriStandarModel, MateriHadron::class.java))
            }
            mStandar2.setOnClickListener { focusOnBottom() }
            footnote1.setOnClickListener {
                startActivity(Intent(this@MateriStandarModel, MateriTimeline::class.java))
            }
        }
    }
    private fun focusOnBottom() {
        binding.latar.isFocusableInTouchMode = true
        binding.latar.smoothScrollTo(0, binding.latar.height)
        binding.latar.fullScroll(View.FOCUS_DOWN)
    }
    private fun openImageViewer(url : String, desc : String) {
        val Editor:SharedPreferences.Editor = sharedPred.edit()
        Editor.putString("url", url)
        Editor.putString("desc", desc)
        Editor.putInt("id", 1)
        Editor.apply()
        val intent = Intent(this, ImageViewerMateriActivity::class.java)
        startActivity(intent)
    }

    private fun gantiLatar(greenRead: Int, pressed: Int) {
        val latarBaru: Int = greenRead
        val tombol: Int = pressed
        val Editor:SharedPreferences.Editor = sharedPred.edit()
        Editor.putInt("gantiLatar", latarBaru)
        Editor.putInt("tombolTerpilih", tombol)
        Editor.apply()
        loadLatarBaru()
    }

    private fun loadLatarBaru() {
        val sharedLatarId = sharedPred.getInt("gantiLatar", 0)
        val sharedTombolId = sharedPred.getInt("tombolTerpilih", 3)
        if (sharedLatarId.equals(0) && sharedTombolId.equals(3)){
            binding.latar.setBackgroundColor(getColor(R.color.white))
        } else {
            binding.latar.setBackgroundColor(getColor(sharedLatarId))
            binding.toggleGroupColor.check(sharedTombolId)
        }
    }

    private fun gantiUkuran(fontParagrafSmall: Int, checkedButtonId: Int) {
        val ukuranBaru: Int = fontParagrafSmall
        val tombolUkuran : Int = checkedButtonId
        val Editor:SharedPreferences.Editor = sharedPred.edit()
        Editor.putInt("ukuranBaru", ukuranBaru)
        Editor.putInt("tombolUkuranTerpilih", tombolUkuran)
        Editor.apply()
        loadUkuranbaru()
    }

    private fun loadUkuranbaru(){
        val sharedUkuranId = sharedPred.getInt("ukuranBaru", 0)
        val sharedTombolUkuranId = sharedPred.getInt("tombolUkuranTerpilih", 3)
        if (sharedUkuranId.equals(0)){
            binding.mStandar.setTextAppearance(R.style.FontParagraf)
            binding.mStandar2.setTextAppearance(R.style.FontParagraf)
            binding.mStandar3.setTextAppearance(R.style.FontParagraf)
            binding.mStandar4.setTextAppearance(R.style.FontParagraf)
            binding.mStandar5.setTextAppearance(R.style.FontParagraf)
            binding.mStandar6.setTextAppearance(R.style.FontParagraf)
            binding.mStandar7.setTextAppearance(R.style.FontParagraf)
            binding.mStandar8.setTextAppearance(R.style.FontParagraf)
        } else {
            binding.mStandar.setTextAppearance(sharedUkuranId)
            binding.mStandar2.setTextAppearance(sharedUkuranId)
            binding.mStandar3.setTextAppearance(sharedUkuranId)
            binding.mStandar4.setTextAppearance(sharedUkuranId)
            binding.mStandar5.setTextAppearance(sharedUkuranId)
            binding.mStandar6.setTextAppearance(sharedUkuranId)
            binding.mStandar7.setTextAppearance(sharedUkuranId)
            binding.mStandar8.setTextAppearance(sharedUkuranId)
            binding.toggleGroup.check(sharedTombolUkuranId)
        }
    }
}