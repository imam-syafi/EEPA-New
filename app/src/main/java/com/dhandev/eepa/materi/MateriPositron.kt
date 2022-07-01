package com.dhandev.eepa.materi

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityMateriPositronBinding
import com.dhandev.eepa.helper.glideImage
import com.dhandev.eepa.ui.imageViewer.ImageViewerMateriActivity


class MateriPositron : AppCompatActivity() {

    private lateinit var binding: ActivityMateriPositronBinding
    private lateinit var sharedPred : SharedPreferences
    private lateinit var sharedPredLastRead : SharedPreferences
    var URL : String = "https://docs.google.com/uc?id=1YG6H-JdH9f7Ve82qbxb5Ba35f8xB4gXA"
    var URL2 = "https://docs.google.com/uc?id=1YNNe_DU-bm3NdgveE_TwyeTynxPeUPo2"
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materi_positron)

        binding = ActivityMateriPositronBinding.inflate(layoutInflater)
        sharedPred = this.getSharedPreferences("Tampilan", MODE_PRIVATE)
        sharedPredLastRead = this.getSharedPreferences("User", MODE_PRIVATE)
        loadUkuranbaru()
        loadLatarBaru()

        val editLastRead = sharedPredLastRead.edit()
        editLastRead.putInt("subMateri", 8)
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

            glideImage.load(this@MateriPositron, URL, gambar)
            glideImage.load(this@MateriPositron, URL2, gambar2)
            val desc = tvCaption1.text.toString()
            val desc2 = tvCaption2.text.toString()
            gambar.setOnClickListener {
                openImageViewer(URL, desc)
            }
            gambar2.setOnClickListener {
                openImageViewer(URL2, desc2)
            }

//            body1.transformationMethod = LinkTransformationMethod()
//            body1.movementMethod = LinkMovementMethod.getInstance()

            //buat tittle berjalan
            title.ellipsize = TextUtils.TruncateAt.MARQUEE
            title.isSingleLine = true
            title.marqueeRepeatLimit = -1
            title.isSelected = true


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

    private fun focusOnTop() {
        binding.latar.isFocusableInTouchMode = true
        binding.latar.smoothScrollTo(0,0)
        binding.latar.fullScroll(View.FOCUS_UP)
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
            binding.mPositron.setTextAppearance(R.style.FontParagraf)
            binding.mPositron2.setTextAppearance(R.style.FontParagraf)
            binding.mPositron3.setTextAppearance(R.style.FontParagraf)
            binding.body3.setTextAppearance(R.style.FontParagraf)
        } else {
            binding.mPositron.setTextAppearance(sharedUkuranId)
            binding.mPositron2.setTextAppearance(sharedUkuranId)
            binding.mPositron3.setTextAppearance(sharedUkuranId)
            binding.body3.setTextAppearance(sharedUkuranId)
            binding.toggleGroup.check(sharedTombolUkuranId)
        }
    }
}