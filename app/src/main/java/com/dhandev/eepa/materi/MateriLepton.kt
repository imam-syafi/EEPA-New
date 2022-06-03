package com.dhandev.eepa.materi

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.Target
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityMateriLeptonBinding
import com.dhandev.eepa.helper.glideImage
import com.dhandev.eepa.ui.imageViewer.ImageViewerMateriActivity

class MateriLepton : AppCompatActivity() {
    private lateinit var binding: ActivityMateriLeptonBinding
    private lateinit var sharedPred: SharedPreferences
    var URL: String = "https://cds.cern.ch/images/CERN-PHOTO-201802-030-10/file?size=medium"
    var URL2 = "https://docs.google.com/uc?id=1RhYvt9iXmpw5fy6ZIdBTfdHn_zY1gJ3b"
    var page = 1
    var desc =
        "Large Hadron Collider (Penubruk Hadron Raksasa) adalah pemercepat partikel berenergi tinggi terbesar di dunia, fasilitas percobaan paling kompleks yang pernah dibangun, dan mesin tunggal terbesar di dunia."
    var desc2 =
        "Fasilitas-fasilitas yang ada di CERN terdiri dari LHC (Large Hadron Collider), SPS (Super Proton Synchrotron), dan PS (Proton Synchrotron)"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMateriLeptonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPred = this.getSharedPreferences("Tampilan", MODE_PRIVATE)
        loadUkuranbaru()
        loadLatarBaru()

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

            glideImage.load(this@MateriLepton, URL2, gambar2)

            gambar2.setOnClickListener {
                openImageViewer(URL2, desc2)
            }

            penemuanPositron.setOnClickListener {
                startActivity(Intent(this@MateriLepton, MateriPositron::class.java))
            }
            btnNext.setOnClickListener {
                startActivity(Intent(this@MateriLepton, MateriStandarModel::class.java))
            }

            btnPrev.setOnClickListener {
                startActivity(Intent(this@MateriLepton, MateriHadron::class.java))
            }
        }
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
            binding.body1.setTextAppearance(R.style.FontParagraf)
            binding.body3.setTextAppearance(R.style.FontParagraf)
        } else {
            binding.body1.setTextAppearance(sharedUkuranId)
            binding.body3.setTextAppearance(sharedUkuranId)
            binding.toggleGroup.check(sharedTombolUkuranId)
        }
    }
}