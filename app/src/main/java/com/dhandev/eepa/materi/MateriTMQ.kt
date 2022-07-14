package com.dhandev.eepa.materi

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.text.util.Linkify
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityMateriTmqBinding
import com.dhandev.eepa.helper.customTab
import com.dhandev.eepa.helper.glideImage
import com.dhandev.eepa.ui.imageViewer.ImageViewerMateriActivity
import com.skydoves.balloon.*
import com.skydoves.balloon.overlay.BalloonOverlayRoundRect


class MateriTMQ : AppCompatActivity() {

    private lateinit var binding: ActivityMateriTmqBinding
    private lateinit var sharedPred : SharedPreferences
    private lateinit var sharedPredLastRead : SharedPreferences
    var URL : String = "https://cds.cern.ch/images/CERN-PHOTO-201802-030-10/file?size=medium"
    var URL2 = "https://cdn.mos.cms.futurecdn.net/7cvrrJxBe3N4Pfsv8q9oaM.jpg"
    var page = 1
    var desc = "Large Hadron Collider (Penubruk Hadron Raksasa) adalah pemercepat partikel berenergi tinggi terbesar di dunia, fasilitas percobaan paling kompleks yang pernah dibangun, dan mesin tunggal terbesar di dunia."
    var desc2 = "Fasilitas-fasilitas yang ada di CERN terdiri dari LHC (Large Hadron Collider), SPS (Super Proton Synchrotron), PS (Proton Synchrotron), serta fasilitas masa depan dengan keliling 100 km"
    private var pedomanGambar = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materi_tmq)

        binding = ActivityMateriTmqBinding.inflate(layoutInflater)
        sharedPred = this.getSharedPreferences("Tampilan", MODE_PRIVATE)
        sharedPredLastRead = this.getSharedPreferences("User", MODE_PRIVATE)
        loadUkuranbaru()
        loadLatarBaru()

        val editLastRead = sharedPredLastRead.edit()
        editLastRead.putInt("subMateri", 2)
        editLastRead.apply()

        pedomanGambar = sharedPredLastRead.getInt("pedomanGambar", 0)
        if (pedomanGambar==0){
            newSpotlight()
        }

        binding.apply{
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
                        R.id.btnGreen ->  gantiLatar(R.color.greenRead, toggleGroupColor.checkedButtonId)
                        R.id.btnPeach -> gantiLatar(R.color.peachRead, toggleGroupColor.checkedButtonId)
                        R.id.btnOrange -> gantiLatar(R.color.orangeRead, toggleGroupColor.checkedButtonId)
                    }
                } else {
                    if (toggleGroup.checkedButtonId == View.NO_ID){
                        gantiLatar(R.color.white, toggleGroupColor.checkedButtonId)
                    }
                }
            }

            glideImage.load(this@MateriTMQ, URL, gambar)
            gambar.setOnClickListener {
                openImageViewer(URL, desc)
            }

            btnNext.setOnClickListener {
                startActivity(Intent(this@MateriTMQ, MateriHadron::class.java))
                finish()
            }

            btnPrev.setOnClickListener {
                startActivity(Intent(this@MateriTMQ, MateriSubatomik::class.java))
                finish()
            }

            btnHome.setOnClickListener {
                startActivity(Intent(this@MateriTMQ, MateriActivity::class.java))
                finish()
            }

//            body1.transformationMethod = LinkTransformationMethod()
//            body1.movementMethod = LinkMovementMethod.getInstance()

            //buat tittle berjalan
            title.text = getString(R.string._2_teori_medan_kuantum)
            title.ellipsize = TextUtils.TruncateAt.MARQUEE
            title.isSingleLine = true
            title.marqueeRepeatLimit = -1
            title.isSelected = true


            Linkify.addLinks(body1, Linkify.ALL)
//            body2.movementMethod = BetterLinkMovementMethod.getInstance()
//            body2.movementMethod = BetterLinkMovementMethod.newInstance().apply {
//                setOnLinkClickListener { textView, url ->
//                    customTab.open(this@MateriTMQ, url)
//                    true
//                }
//                setOnLinkLongClickListener { textView, url ->
//                    Toast.makeText(this@MateriTMQ, getString(R.string.link_hint), Toast.LENGTH_SHORT).show()
//                    true
//                }
//            }
            mSubatomik2.setOnClickListener { focusOnBottom() }
            footnote1.setOnClickListener { customTab.open(this@MateriTMQ, "https://home.cern/science/physics/higgs-boson") }
        }
    }

    private fun newSpotlight() {
        pedomanGambar++
        val Editor:SharedPreferences.Editor = sharedPredLastRead.edit()
        Editor.putInt("pedomanGambar", pedomanGambar).apply()
        binding.apply {
            val balloon = Balloon.Builder(this@MateriTMQ)
                .setWidth(BalloonSizeSpec.WRAP)
                .setHeight(BalloonSizeSpec.WRAP)
                .setText("Tekan untuk memperbesar")
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
                .setLifecycleOwner(this@MateriTMQ)
                .setBalloonHighlightAnimation(BalloonHighlightAnimation.SHAKE)
                .build()

            gambar.showAlignBottom(balloon)
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
            binding.body1.setTextAppearance(R.style.FontParagraf)
            binding.mSubatomik2.setTextAppearance(R.style.FontParagraf)
            binding.mSubatomik22.setTextAppearance(R.style.FontParagraf)
        } else {
            binding.body1.setTextAppearance(sharedUkuranId)
            binding.mSubatomik2.setTextAppearance(sharedUkuranId)
            binding.mSubatomik22.setTextAppearance(sharedUkuranId)
            binding.toggleGroup.check(sharedTombolUkuranId)
        }
    }
}