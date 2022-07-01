package com.dhandev.eepa.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.dhandev.eepa.R
import com.google.android.material.progressindicator.LinearProgressIndicator
import java.util.*

class ViewPagerAdapter(val context: Context, val imageList: List<Int>, val judulList: List<String>, val descList: List<String>, val progress: List<Int>): PagerAdapter() {
    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mLayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView: View = mLayoutInflater.inflate(R.layout.view_pager, container, false)
        val imageView = itemView.findViewById<View>(R.id.gambarOnBoarding) as ImageView
        val title = itemView.findViewById<View>(R.id.judulOnBoarding) as TextView
        val desc = itemView.findViewById<View>(R.id.descOnBoarding) as TextView
        val progressBar = itemView.findViewById<View>(R.id.progressBarLinear) as LinearProgressIndicator
        imageView.setImageResource(imageList[position])
        title.text = judulList[position]
        desc.text = descList[position]
        progressBar.progress = progress[position]

        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}