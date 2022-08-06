package com.dhandev.eepa.helper

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dhandev.eepa.databinding.BaseListItemBinding
import com.lriccardo.timelineview.TimelineAdapter
import com.lriccardo.timelineview.TimelineView

class BaseAdapter(val context: Context, val penemu : List<String>, val items: List<String>, val desc: List<CharSequence>) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>(), TimelineAdapter {

    private var onItemClickCallback : OnItemClickCallback? = null
    lateinit var mContext: Context

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = BaseListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        mContext = parent.context

        return BaseViewHolder(view)
    }

    inner class BaseViewHolder(val binding: BaseListItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(penemu: String, items: String, desc: CharSequence){
            binding.apply {
                root.setOnClickListener {
                    onItemClickCallback?.onItemClicked(penemu, items, desc)
                }
            }
            Glide.with(context)
                .load(penemu)
                .circleCrop()
                .into(binding.penemu)
            glideImage
            binding.title.text = items
            binding.desc.text = desc
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(penemu[position], items[position], desc[position])
        holder.itemView.setOnClickListener {
//            Toast.makeText(mContext, items[position], Toast.LENGTH_SHORT).show()
            when(holder.binding.title.text){
                "1897 - Elektron" -> customTab.open(mContext, "https://en.wikipedia.org/wiki/Electron")
//                "1905 - Foton"
//                "1919 - Proton"
//                "1932 - Neutron"
//                "1932 - Positron"
//                "1937 - Muon"
//                "1947 - Pion"
//                "1947 - Kaon"
//                "1950 - Baryon Lambda"
//                "1955 - Antiproton"
//                "1956 - Neutrino Elektron"
//                "1962 - Neutrino Muon"
//                "1964 - Baryon Xi"
//                "1975 - Tau"
//                "1977 - Meson Upsilon"
//                "1979 - Gluon"
//                "1983 - Boson W dan Z"
//                "1995 - Kuark Top"
//                "1995 - Antihidrogen"
//                "2000 - Neutrino Tau"
//                "2012 - Boson Higgs"
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return 0;
    }

    override fun getItemCount(): Int = items.size

    override fun getIndicatorStyle(position: Int): TimelineView.IndicatorStyle? {
        if (position < 1)
            return TimelineView.IndicatorStyle.Checked
        else return TimelineView.IndicatorStyle.Empty
    }

    override fun getLineStyle(position: Int): TimelineView.LineStyle? {
        if (position >= 1)
            return TimelineView.LineStyle.Normal
        return super.getLineStyle(position)
    }

    override fun getLinePadding(position: Int): Float? {
        if (position > 1)
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                16f,
                Resources.getSystem().displayMetrics
            )
        return super.getLinePadding(position)
    }

    interface OnItemClickCallback{
        fun onItemClicked(penemu: String, items: String, desc: CharSequence)
    }
}