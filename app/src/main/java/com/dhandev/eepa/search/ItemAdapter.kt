package com.dhandev.eepa.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.dhandev.eepa.R
import java.util.*

class ItemAdapter(private val listSearch: ArrayList<Item>) : RecyclerView.Adapter<ItemAdapter.ListViewHolder>(), Filterable {

    var listSearchFilter = ArrayList<Item>()
    init {
        listSearchFilter = listSearch
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listSearchFilter[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description
    }

    override fun getItemCount(): Int = listSearchFilter.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
        private val context: Context? = itemView.context

        fun ListViewHolder(itemView: View){
            super.itemView
            val context = itemView.context
            itemView.isClickable = true
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            Toast.makeText(context, "The Item Clicked is: ",Toast.LENGTH_SHORT).show()
        }
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
                        if (row.toString().trim().lowercase(Locale.ROOT).contains(charSearch.trim().lowercase(Locale.ROOT))) {
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