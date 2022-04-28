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

class ReferensiActivity : AppCompatActivity() {
    private lateinit var binding : ActivityReferensiBinding
    var url : String = ""
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
                customTabs()
            }
            bukuAlonso.setOnClickListener{
                url = "https://www.google.co.id/books/edition/Fundamental_University_Physics_Quantum_a/CPZQAAAAMAAJ?hl=id&gbpv=0&bsq=fundamental%20university%20physics%20alonso%20finn"
                customTabs()
            }
        }
    }

    private fun customTabs() {
        val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
        val colorInt : Int = Color.parseColor("#309AE3")
        var defaulColor : CustomTabColorSchemeParams = CustomTabColorSchemeParams.Builder().setToolbarColor(colorInt).build()
        builder.setDefaultColorSchemeParams(defaulColor)
        builder.setShowTitle(true)
        //ganti close button icon, default X
        AppCompatResources.getDrawable(this, R.drawable.arrow_back)?.let {
            DrawableCompat.setTint(it, Color.WHITE)
            builder.setCloseButtonIcon(it.toBitmap())
        }
        builder.setStartAnimations(this, R.anim.slide_in_left, R.anim.slide_out_left);
        builder.setExitAnimations(this, R.anim.slide_in_left, R.anim.slide_out_right);
//        this.let { startAnim -> builder.setStartAnimations(startAnim, androidx.navigation.ui.R.anim.nav_default_enter_anim, androidx.navigation.ui.R.anim.nav_default_exit_anim) }
//        this.let { exitAnim -> builder.setExitAnimations(exitAnim, androidx.navigation.ui.R.anim.nav_default_pop_enter_anim, androidx.navigation.ui.R.anim.nav_default_pop_exit_anim) }
        val customTabsIntent: CustomTabsIntent = builder.build()
        this.let { it1 -> customTabsIntent.launchUrl(it1, Uri.parse(url)) }
    }
}