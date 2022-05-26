package com.dhandev.eepa.helper

import android.graphics.Rect
import android.text.Spannable
import android.text.Spanned
import android.text.method.TransformationMethod
import android.text.style.URLSpan
import android.text.util.Linkify
import android.util.Patterns
import android.view.View
import android.widget.TextView


class LinkTransformationMethod : TransformationMethod {
    private var linkifyOptions = 0

    fun LinkTransformationMethod(linkifyOptions: Int) {
        this.linkifyOptions = linkifyOptions
    }

    override fun getTransformation(source: CharSequence?, view: View?): CharSequence? {
        if (view is TextView) {
            val textView = view
            Linkify.addLinks(textView, linkifyOptions)
            if (textView.text == null || textView.text !is Spannable) {
                return source
            }
            val text = textView.text as Spannable
            val spans = text.getSpans(0, textView.length(), URLSpan::class.java)
            for (i in spans.indices.reversed()) {
                val oldSpan = spans[i]
                val start = text.getSpanStart(oldSpan)
                val end = text.getSpanEnd(oldSpan)
                val url = oldSpan.url
                if (!Patterns.WEB_URL.matcher(url).matches()) {
                    continue
                }
                text.removeSpan(oldSpan)
                text.setSpan(CustomTabsURLSpan(url), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            return text
        }
        return source
    }

    override fun onFocusChanged(
        view: View?,
        sourceText: CharSequence?,
        focused: Boolean,
        direction: Int,
        previouslyFocusedRect: Rect?
    ) {
    }
}