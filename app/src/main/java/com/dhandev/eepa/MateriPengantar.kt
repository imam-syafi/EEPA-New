package com.dhandev.eepa

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat
import com.dhandev.eepa.databinding.ActivityMateriPengantarBinding


class MateriPengantar : AppCompatActivity() {

    private lateinit var binding: ActivityMateriPengantarBinding
    private lateinit var sharedPred : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materi_pengantar)

        binding = ActivityMateriPengantarBinding.inflate(layoutInflater)
        sharedPred = this.getSharedPreferences("Tampilan", MODE_PRIVATE)
        loadUkuranbaru()

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
                        R.id.btnSmall -> gantiUkuran(R.style.FontParagrafSmall)
                        R.id.btnMedium -> gantiUkuran(R.style.FontParagraf)
                        R.id.btnLarge -> gantiUkuran(R.style.FontParagrafLarge)
//                        R.id.btnMedium -> body1.setTextAppearance(R.style.FontParagraf)
//                        R.id.btnLarge -> body1.setTextAppearance(R.style.FontParagrafLarge)
                    }
                } else {
                    if (toggleGroup.checkedButtonId == View.NO_ID){
                        body1.setTextAppearance(R.style.FontParagraf)
                    }
                }

            }

            toggleGroupColor.addOnButtonCheckedListener{toggleGroup, checkedId, isChecked ->
                if (isChecked){
                    when(checkedId){
                        R.id.btnGreen -> latar.setBackgroundColor(getColor(R.color.greenRead))
                        R.id.btnPeach -> latar.setBackgroundColor(getColor(R.color.peachRead))
                        R.id.btnOrange -> latar.setBackgroundColor(getColor(R.color.orangeRead))
                    }
                } else {
                    if (toggleGroup.checkedButtonId == View.NO_ID){
                        latar.setBackgroundColor(getColor(R.color.white))
                    }
                }
            }
        }
    }

    private fun gantiUkuran(fontParagrafSmall: Int) {
        val ukuranBaru: Int = fontParagrafSmall
        val Editor:SharedPreferences.Editor = sharedPred.edit()
        Editor.putInt("ukuranBaru", ukuranBaru)
        Editor.apply()
        Editor.commit()
        loadUkuranbaru()
    }

    private fun loadUkuranbaru(){
        val sharedId = sharedPred.getInt("ukuranBaru", 0)
        if (sharedId.equals(0)){
            binding.body1.setTextAppearance(R.style.FontParagraf)
        } else {
            binding.body1.setTextAppearance(sharedId)
        }
    }

}