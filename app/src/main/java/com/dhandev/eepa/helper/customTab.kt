package com.dhandev.eepa.helper

import android.content.Context
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.content.res.AppCompatResources
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.toBitmap
import com.dhandev.eepa.R

object customTab {
    fun open(context: Context, dataUrl: String){
        val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
        val colorInt : Int = Color.parseColor("#309AE3")
        val defaulColor : CustomTabColorSchemeParams = CustomTabColorSchemeParams.Builder().setToolbarColor(colorInt).build()
        builder.setDefaultColorSchemeParams(defaulColor)
        builder.setShowTitle(true)
        //ganti close button icon, default X
        AppCompatResources.getDrawable(context, R.drawable.arrow_back)?.let {
            DrawableCompat.setTint(it, Color.WHITE)
            builder.setCloseButtonIcon(it.toBitmap())
        }
        builder.setStartAnimations(context, R.anim.slide_in_left, R.anim.slide_out_left);
        builder.setExitAnimations(context, R.anim.slide_in_left, R.anim.slide_out_right);
        val customTabsIntent: CustomTabsIntent = builder.build()
        context.let { it1 -> customTabsIntent.launchUrl(it1, Uri.parse(dataUrl)) }
    }
}