package com.dhandev.eepa.helper

import android.os.Parcel
import android.text.style.URLSpan
import android.view.View


class CustomTabsURLSpan : URLSpan {
    constructor(url: String?) : super(url) {}
    constructor(src: Parcel?) : super(src!!) {}

    override fun onClick(p0: View) {
        val url = getURL()
        // attempt to open with custom tabs, if that fails, call super.onClick
//        super.onClick(p0)
    }
}