package com.dhandev.eepa.materi

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.util.Linkify
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityMateriHadronBinding
import com.dhandev.eepa.helper.customTab
import com.dhandev.eepa.helper.glideImage
import com.dhandev.eepa.ui.imageViewer.ImageViewerMateriActivity

class MateriHadron : AppCompatActivity() {
    private lateinit var binding: ActivityMateriHadronBinding
    private lateinit var sharedPred : SharedPreferences
    private lateinit var sharedPredLastRead : SharedPreferences
    var URL : String = "https://docs.google.com/uc?id=1QYZnzK2G8UdSegnq9Xg7c7b5kOMmEO6-"
    var URL2 = "https://docs.google.com/uc?id=1QZQtDK-fm-HKcNa3YXAz0Tf8TCMaMjTr"
    var page = 1
    private lateinit var desc : String
    private lateinit var desc2 : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMateriHadronBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPred = this.getSharedPreferences("Tampilan", MODE_PRIVATE)
        sharedPredLastRead = this.getSharedPreferences("User", MODE_PRIVATE)
        loadUkuranbaru()
        loadLatarBaru()

        val editLastRead = sharedPredLastRead.edit()
        editLastRead.putInt("subMateri", 3)
        editLastRead.apply()

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
            desc = tvCaption1.text.toString()
            desc2 = ""

            glideImage.load(this@MateriHadron, URL, gambar)
//            glideImage2()
            gambar.setOnClickListener {
                openImageViewer(URL, desc)
            }
//            gambar2.setOnClickListener {
//                openImageViewer(URL2, desc2)
//            }

            btnNext.setOnClickListener {
                startActivity(Intent(this@MateriHadron, MateriLepton::class.java))
            }

            btnPrev.setOnClickListener {
                startActivity(Intent(this@MateriHadron, MateriTMQ::class.java))
            }

            btnHome.setOnClickListener {
                startActivity(Intent(this@MateriHadron, MateriActivity::class.java))
            }
//            body1.transformationMethod = LinkTransformationMethod()
//            body1.movementMethod = LinkMovementMethod.getInstance()


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
            mHadronKuark1.setOnClickListener { focusOnBottom() }
            mHadronKuark2.setOnClickListener { focusOnBottom() }
            footnote1.setOnClickListener { customTab.open(this@MateriHadron, "https://en.wikipedia.org/wiki/Free_particle") }
            footnote2.setOnClickListener { customTab.open(this@MateriHadron, "https://www.thinksphysics.com/2020/07/eksperimen-tetes-minyak-milikan-dalam-menentukan-muatan-elektron.html") }
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
            binding.mHadron1.setTextAppearance(R.style.FontParagraf)
            binding.mHadron2.setTextAppearance(R.style.FontParagraf)
            binding.mHadron3.setTextAppearance(R.style.FontParagraf)
            binding.mHadronKuark1.setTextAppearance(R.style.FontParagraf)
            binding.mHadronKuark2.setTextAppearance(R.style.FontParagraf)
            binding.mHadronKuark3.setTextAppearance(R.style.FontParagraf)
            binding.mHadronKuark5.setTextAppearance(R.style.FontParagraf)
            binding.mHadronMeson1.setTextAppearance(R.style.FontParagraf)
            binding.mHadronMeson2.setTextAppearance(R.style.FontParagraf)
            binding.mHadronMeson3.setTextAppearance(R.style.FontParagraf)
        } else {
            binding.body1.setTextAppearance(sharedUkuranId)
            binding.mHadron1.setTextAppearance(sharedUkuranId)
            binding.mHadron2.setTextAppearance(sharedUkuranId)
            binding.mHadron3.setTextAppearance(sharedUkuranId)
            binding.mHadronKuark1.setTextAppearance(sharedUkuranId)
            binding.mHadronKuark2.setTextAppearance(sharedUkuranId)
            binding.mHadronKuark3.setTextAppearance(sharedUkuranId)
            binding.mHadronKuark5.setTextAppearance(sharedUkuranId)
            binding.mHadronMeson1.setTextAppearance(sharedUkuranId)
            binding.mHadronMeson2.setTextAppearance(sharedUkuranId)
            binding.mHadronMeson3.setTextAppearance(sharedUkuranId)
            binding.toggleGroup.check(sharedTombolUkuranId)
        }
    }
}