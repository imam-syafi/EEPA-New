package com.dhandev.eepa.search

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.dhandev.eepa.R
import com.dhandev.eepa.addition.dummyActivity
import com.dhandev.eepa.databinding.ItemSearchBinding
import com.dhandev.eepa.helper.customTab
import com.dhandev.eepa.materi.*
import java.util.*
import kotlin.collections.ArrayList

class ItemAdapter(private val listSearch: ArrayList<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var listSearchFilter = ArrayList<Item>()
    lateinit var mContext: Context
    lateinit var charSearch: String

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
            when(name){
                "Partikel Subatomik" ->{
                    val intent = Intent(mContext, MateriSubatomik::class.java)
                    intent.putExtra("highlight", charSearch)
                    mContext.startActivity(intent)
                }
                "Teori Medan Kuantum" ->{mContext.startActivity(Intent(mContext, MateriTMQ::class.java))}
                "Hadron" ->{mContext.startActivity(Intent(mContext, MateriHadron::class.java))}
                "Lepton" ->{mContext.startActivity(Intent(mContext, MateriLepton::class.java))}
                "Model Standard" ->{mContext.startActivity(Intent(mContext, MateriStandarModel::class.java))}
                "Perkembangan Terkini" ->{mContext.startActivity(Intent(mContext, MateriTerkini::class.java))}

                else -> {
//                    val intent = Intent(mContext, dummyActivity::class.java)
//                    intent.putExtra("name", name)
//                    intent.putExtra("desc", description)
//                    mContext.startActivity(intent)
                    customTab.open(mContext, "https://www.google.com/search?q=$name&oq=a&aqs=chrome..69i57j69i60l6j69i65.1322j0j9&sourceid=chrome&ie=UTF-8")
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listSearchFilter.size
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    listSearchFilter = listSearch
                } else if (listSearch.toString().lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))){
                    val resultList = ArrayList<Item>()
                    for (row in listSearch) {
                        val rowString = row.toString().lowercase(Locale.ROOT)
                        if (rowString.contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    listSearchFilter = resultList
                } else {
                    val dataName = charSearch
                    val dataDescription = mContext.resources.getStringArray(R.array.data_search_engine)
                    val dataPhoto = mContext.resources.obtainTypedArray(R.array.data_photo_)
                    val resultList = ArrayList<Item>()
                    for (i in dataDescription.indices) {
                        val item = Item(dataName,dataDescription[i], dataPhoto.getResourceId(i, -1))
                        resultList.clear()
                        resultList.add(item)
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