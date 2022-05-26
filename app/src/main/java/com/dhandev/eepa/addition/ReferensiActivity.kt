package com.dhandev.eepa.addition

import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.toBitmap
import com.dhandev.eepa.R
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
            Lumen.setOnClickListener {
                url = "https://courses.lumenlearning.com/boundless-chemistry/chapter/history-of-atomic-structure/"
                customTab.open(this@ReferensiActivity, url)
            }
            Khan.setOnClickListener {
                url = "https://www.khanacademy.org/science/chemistry/electronic-structure-of-atoms/history-of-atomic-structure/a/discovery-of-the-electron-and-nucleus"
                customTab.open(this@ReferensiActivity, url)
            }
        }
    }
}