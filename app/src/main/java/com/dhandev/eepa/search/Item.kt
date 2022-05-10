package com.dhandev.eepa.search

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    var name: String,
    var description: String,
    var photo: Int
) : Parcelable