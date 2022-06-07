package com.dhandev.eepa.helper

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.dhandev.eepa.R

object utils {
    fun textColorAndMode(context: Context, iv: TextView, color: Int, mode: Typeface){
        iv.setTextColor(
            ContextCompat.getColor(
                context,
                color
            )
        )
        iv.typeface = mode
    }
}