package com.dhandev.eepa.materi

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.dhandev.eepa.R
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
        loadLatarBaru()

        val fragment: Fragment
        fragment = MiniQuizFragment()
        loadFragmentQuiz(fragment)

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

    private fun loadFragmentQuiz(fragment: MiniQuizFragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.containerQuiz, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
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
//TODO: Buat class khusus pengaturan warna latar dan ukuran
}