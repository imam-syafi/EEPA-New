package com.dhandev.eepa.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ItemSearchBinding
import java.util.*

class ItemAdapter(private val listSearch: ArrayList<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var listSearchFilter = ArrayList<Item>()
    lateinit var mContext: Context

    class ItemHolder(var viewBinding: ItemSearchBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    init {
        listSearchFilter = listSearch
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val sch = ItemHolder(binding)
        mContext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemHolder = holder as ItemHolder
        val (name, description, photo) = listSearchFilter[position]
        itemHolder.viewBinding.imgItemPhoto.setImageResource(photo)
        itemHolder.viewBinding.tvItemDescription.text = description
        itemHolder.viewBinding.tvItemName.text = name
        holder.itemView.setOnClickListener {
            Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return listSearchFilter.size
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    listSearchFilter = listSearch
                } else {
                    val resultList = ArrayList<Item>()
                    for (row in listSearch) {
                        if (row.toString().lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    listSearchFilter = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = listSearchFilter
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listSearchFilter = results?.values as ArrayList<Item>
                notifyDataSetChanged()
            }

        }
    }
}