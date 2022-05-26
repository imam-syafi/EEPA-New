package com.dhandev.eepa.helper

import android.content.Context
import android.graphics.Color
import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent

object customTab {
    fun open(context: Context, dataUrl: String){
        val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
        val colorInt : Int = Color.parseColor("#309AE3")
        val defaulColor : CustomTabColorSchemeParams = CustomTabColorSchemeParams.Builder().setToolbarColor(colorInt).build()
        builder.setDefaultColorSchemeParams(defaulColor)
        context.let { startAnim -> builder.setStartAnimations(startAnim, androidx.navigation.ui.R.anim.nav_default_enter_anim, androidx.navigation.ui.R.anim.nav_default_exit_anim) }
        context.let { exitAnim -> builder.setExitAnimations(exitAnim, androidx.navigation.ui.R.anim.nav_default_pop_enter_anim, androidx.navigation.ui.R.anim.nav_default_pop_exit_anim) }
        val customTabsIntent: CustomTabsIntent = builder.build()
        context.let { it1 -> customTabsIntent.launchUrl(it1, Uri.parse(dataUrl)) }
    }
}