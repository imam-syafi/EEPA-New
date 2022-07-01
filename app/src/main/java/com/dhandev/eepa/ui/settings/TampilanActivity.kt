package com.dhandev.eepa.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityTampilanBinding

class TampilanActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTampilanBinding
    private lateinit var sharedPred : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTampilanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPred = this.getSharedPreferences("Tampilan", MODE_PRIVATE)
        loadUkuranbaru()
        loadLatarBaru()

        binding.apply {
            arrowBack.setOnClickListener {
                onBackPressed()
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
        }
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
        } else {
            binding.body1.setTextAppearance(sharedUkuranId)
            binding.toggleGroup.check(sharedTombolUkuranId)
        }
    }
}