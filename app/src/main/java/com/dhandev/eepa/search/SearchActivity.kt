package com.dhandev.eepa.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivitySearchBinding
import java.lang.ref.WeakReference

class SearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchBinding
    private lateinit var rvItem: RecyclerView
    private val list = ArrayList<Item>()
    var listSearched : List<Item> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            arrowBack.setOnClickListener {
                onBackPressed()
            }
            showSoftKeyboard(editText)

            //Search Method
            editText.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    showFilteredList(newText)
                    return false
                }

            })
            //recyclerView
            rvItem = item
            rvItem.setHasFixedSize(true)
            list.addAll(listItem)
            showRecyclerList()
        }

    }

    private fun showFilteredList(newtext : String?) {
        rvItem.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ItemAdapter(list)
        listHeroAdapter.filter.filter(newtext)
        rvItem.adapter = listHeroAdapter
    }

    private fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED)
        }
    }

    private val listItem: ArrayList<Item>
        get() {
            val dataName = resources.getStringArray(R.array.data_name)
            val dataDescription = resources.getStringArray(R.array.data_description)
            val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
            val listItem = ArrayList<Item>()
            for (i in dataName.indices) {
                val item = Item(dataName[i],dataDescription[i], dataPhoto.getResourceId(i, -1))
                listItem.add(item)
            }
            return listItem
        }

    private fun showRecyclerList() {
        rvItem.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ItemAdapter(list)
        rvItem.adapter = listHeroAdapter
    }

}
