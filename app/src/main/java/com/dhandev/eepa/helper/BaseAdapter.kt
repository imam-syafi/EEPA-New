package com.dhandev.eepa.helper

import android.content.res.Resources
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.BaseListItemBinding
import com.lriccardo.timelineview.TimelineAdapter
import com.lriccardo.timelineview.TimelineView

class BaseAdapter(val items: List<String>, val desc: List<String>) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>(), TimelineAdapter {

    private var onItemClickCallback : OnItemClickCallback? = null
    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = BaseListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BaseViewHolder(view)
    }

    inner class BaseViewHolder(val binding: BaseListItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(items: String, desc: String){
            binding.apply {
                root.setOnClickListener {
                    onItemClickCallback?.onItemClicked(items, desc)
                }
            }
            binding.title.text = items
            binding.desc.text = desc
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(items[position], desc[position])
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
        fun onItemClicked(items: String, desc: String)
    }
}