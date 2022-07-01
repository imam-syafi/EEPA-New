package com.dhandev.eepa.addition

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dhandev.eepa.databinding.ActivityReferensiBinding
import com.dhandev.eepa.helper.customTab

class ReferensiActivity : AppCompatActivity() {
    private lateinit var binding : ActivityReferensiBinding
    private var url : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReferensiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            arrowBack.setOnClickListener {
                onBackPressed()
            }
            bukuKenneth.setOnClickListener{
                url = "https://www.google.co.id/books/edition/Modern_Physics/0as7AQAAIAAJ?hl=id&gbpv=0&bsq=kenneth%20krane%20physics"
                customTab.open(this@ReferensiActivity, url)
            }
            bukuAlonso.setOnClickListener{
                url = "https://www.google.co.id/books/edition/Fundamental_University_Physics_Quantum_a/CPZQAAAAMAAJ?hl=id&gbpv=0&bsq=fundamental%20university%20physics%20alonso%20finn"
                customTab.open(this@ReferensiActivity, url)
            }
            wikipediaElementer.setOnClickListener {
                url = "https://en.wikipedia.org/wiki/Elementary_particle"
                customTab.open(this@ReferensiActivity, url)
            }
        }
    }
}