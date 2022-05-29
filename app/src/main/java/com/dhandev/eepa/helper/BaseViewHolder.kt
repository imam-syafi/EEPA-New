package com.dhandev.eepa.helper

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dhandev.eepa.R

class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title = itemView.findViewById<TextView>(R.id.title)
}