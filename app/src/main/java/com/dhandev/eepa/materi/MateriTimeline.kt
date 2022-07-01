package com.dhandev.eepa.materi

import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import android.text.util.Linkify
import android.util.TypedValue
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityMateriTimelineBinding
import com.dhandev.eepa.helper.BaseAdapter
import com.dhandev.eepa.helper.customTab
import com.lriccardo.timelineview.TimelineDecorator
import me.saket.bettermovementmethod.BetterLinkMovementMethod

class MateriTimeline : AppCompatActivity() {
    private lateinit var binding: ActivityMateriTimelineBinding
    var listItem = mutableListOf<String>()
    var listDesc = mutableListOf<CharSequence>()
    private lateinit var sharedPredLastRead : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMateriTimelineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPredLastRead = this.getSharedPreferences("User", MODE_PRIVATE)

        val editLastRead = sharedPredLastRead.edit()
        editLastRead.putInt("subMateri", 7)
        editLastRead.apply()

        binding.apply {
            arrowBack.setOnClickListener { onBackPressed() }
            Linkify.addLinks(sumber, Linkify.ALL)
            sumber.movementMethod = BetterLinkMovementMethod.newInstance().apply {
                setOnLinkClickListener { textView, url ->
                    customTab.open(this@MateriTimeline, url)
                    true
                }
                setOnLinkLongClickListener { textView, url ->
                    Toast.makeText(this@MateriTimeline, getString(R.string.link_hint), Toast.LENGTH_SHORT).show()
                    true
                }
            }

        }

        binding.timeline.let {
            it.layoutManager =LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            listTittle()
            listDesc()
            it.adapter = BaseAdapter(ArrayList(listItem), ArrayList(listDesc))
            val colorPrimary = TypedValue()
            val theme: Resources.Theme = getTheme()
            theme.resolveAttribute(androidx.appcompat.R.attr.colorPrimary, colorPrimary, true)

            it.addItemDecoration(
                TimelineDecorator(
                    position = TimelineDecorator.Position.Left,
                    indicatorColor = colorPrimary.data,
                    lineColor = colorPrimary.data
                )
            )
        }

    }

    private fun listTittle() {
        val item1 = "1897 - Elektron"
        val item2 = "1905 - Foton"
        val item3 = "1919 - Proton"
        val item4 = "1932 - Neutron"
        val item5 = "1932 - Positron"
        val item6 = "1937 - Muon"
        val item7 = "1947 - Pion"
        val item8 = "1947 - Kaon"
        val item9 = "1950 - Baryon Lambda"
        val item10 = "1955 - Antiproton"
        val item11 = "1956 - Neutrino Elektron"
        val item12 = "1962 - Neutrino Muon"
        val item13 = "1964 - Baryon Xi"
        val item14 = "1975 - Tau"
        val item15 = "1977 - Meson Upsilon"
        val item16 = "1979 - Gluon"
        val item17 = "1983 - Boson W dan Z"
        val item18 = "1995 - Kuark Top"
        val item19 = "1995 - Antihidrogen"
        val item20 = "2000 - Neutrino Tau"
        val item21 = "2012 - Boson Higgs"
        listItem.add(item1)
        listItem.add(item2)
        listItem.add(item3)
        listItem.add(item4)
        listItem.add(item5)
        listItem.add(item6)
        listItem.add(item7)
        listItem.add(item8)
        listItem.add(item9)
        listItem.add(item10)
        listItem.add(item11)
        listItem.add(item12)
        listItem.add(item13)
        listItem.add(item14)
        listItem.add(item15)
        listItem.add(item16)
        listItem.add(item17)
        listItem.add(item18)
        listItem.add(item19)
        listItem.add(item20)
        listItem.add(item21)
    }

    private fun listDesc() {
        val desc1 = getText(R.string.t_desc1)
        val desc2 = getText(R.string.t_desc2)
        val desc3 = getText(R.string.t_desc3)
        val desc4 = getText(R.string.t_desc4)
        val desc5 = getText(R.string.t_desc5)
        val desc6 = getText(R.string.t_desc6)
        val desc7 = getText(R.string.t_desc7)
        val desc8 = getText(R.string.t_desc8)
        val desc9 = getText(R.string.t_desc9)
        val desc10 = getText(R.string.t_desc10)
        val desc11 = getText(R.string.t_desc11)
        val desc12 = getText(R.string.t_desc12)
        val desc13 = getText(R.string.t_desc13)
        val desc14 = getText(R.string.t_desc14)
        val desc15 = getText(R.string.t_desc15)
        val desc16 = getText(R.string.t_desc16)
        val desc17 = getText(R.string.t_desc17)
        val desc18 = getText(R.string.t_desc18)
        val desc19 = getText(R.string.t_desc19)
        val desc20 = getText(R.string.t_desc20)
        val desc21 = getText(R.string.t_desc21)
        listDesc.add(desc1)
        listDesc.add(desc2)
        listDesc.add(desc3)
        listDesc.add(desc4)
        listDesc.add(desc5)
        listDesc.add(desc6)
        listDesc.add(desc7)
        listDesc.add(desc8)
        listDesc.add(desc9)
        listDesc.add(desc10)
        listDesc.add(desc11)
        listDesc.add(desc12)
        listDesc.add(desc13)
        listDesc.add(desc14)
        listDesc.add(desc15)
        listDesc.add(desc16)
        listDesc.add(desc17)
        listDesc.add(desc18)
        listDesc.add(desc19)
        listDesc.add(desc20)
        listDesc.add(desc21)
    }

}