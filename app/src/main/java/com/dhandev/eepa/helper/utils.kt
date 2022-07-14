package com.dhandev.eepa.helper

import android.app.AlertDialog
import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ImageSpan
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.util.concurrent.atomic.AtomicBoolean


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
    fun Justify(textView: TextView) {
        val isJustify = AtomicBoolean(false)
        val textString = textView.text.toString()
        val textPaint = textView.paint
        val builder = SpannableStringBuilder()
        textView.post {
            if (!isJustify.get()) {
                val lineCount = textView.lineCount
                val textViewWidth = textView.width
                for (i in 0 until lineCount) {
                    val lineStart = textView.layout.getLineStart(i)
                    val lineEnd = textView.layout.getLineEnd(i)
                    val lineString = textString.substring(lineStart, lineEnd)
                    if (i == lineCount - 1) {
                        builder.append(SpannableString(lineString))
                        break
                    }
                    val trimSpaceText = lineString.trim { it <= ' ' }
                    val removeSpaceText = lineString.replace(" ".toRegex(), "")
                    val removeSpaceWidth = textPaint.measureText(removeSpaceText)
                    val spaceCount = (trimSpaceText.length - removeSpaceText.length).toFloat()
                    val eachSpaceWidth = (textViewWidth - removeSpaceWidth) / spaceCount
                    val spannableString = SpannableString(lineString)
                    for (j in 0 until trimSpaceText.length) {
                        val c = trimSpaceText[j]
                        if (c == ' ') {
                            val drawable: Drawable = ColorDrawable(0x00ffffff)
                            drawable.setBounds(0, 0, eachSpaceWidth.toInt(), 0)
                            val span = ImageSpan(drawable)
                            spannableString.setSpan(
                                span,
                                j,
                                j + 1,
                                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                            )
                        }
                    }
                    builder.append(spannableString)
                }
                textView.text = builder
                isJustify.set(true)
            }
        }
    }

    fun dialog(context: Context, icon: Int, title: String, message: String, url: String){
        AlertDialog.Builder(context)
            .setTitle(title)
            .setIcon(icon)
            .setMessage(message)
            .setPositiveButton("Kunjungi situs") { dialog, id ->
                customTab.open(context, url)
            }
            .setNegativeButton("Tutup") { dialog, id ->
                dialog.cancel()
            }
            .show()
    }

    fun dialogHasil(context: Context, icon: Int, title: String, message: String){
        AlertDialog.Builder(context)
            .setTitle(title)
            .setIcon(icon)
            .setMessage(message)
            .setPositiveButton("Tutup") { dialog, id ->
                dialog.cancel()
            }
            .show()
    }
}