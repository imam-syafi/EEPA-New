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
    var listPenemu = mutableListOf<String>()
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
            listPenemu()
            it.adapter = BaseAdapter(this, ArrayList(listPenemu), ArrayList(listItem), ArrayList(listDesc))
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

    private fun listPenemu() {
        val item1 = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/JJ_Thomson_%28Nobel%29.jpg/220px-JJ_Thomson_%28Nobel%29.jpg"
        val item2 = "https://lp2m.uma.ac.id/wp-content/uploads/2018/11/Albert-Einstein-jpg-385x385.jpg"
        val item3 = "https://www.tentorku.com/wp-content/uploads/2015/08/Ernest-Rutherford.jpg"
        val item4 = "https://www.aps.org/publications/apsnews/200705/images/chadwick_web.jpg"
        val item5 = "https://www.nobelprize.org/images/anderson-13005-content-portrait-mobile-tiny.jpg"
        val item6 = "https://history.aip.org/phn/Photos/anderson_herbert_c1_cropped.jpg"
        val item7 = "https://collectionimages.npg.org.uk/std/mw227008/CF-Powell.jpg"
        val item8 = "https://www.durham.ac.uk/media/durham-university/departments-/physics/major-lecture-series/George-Rochester.jpg"
        val item9 = "https://sdm.uajy.ac.id/wp-content/uploads/2021/06/No_Image_Available.jpg"
        val item10 = "https://upload.wikimedia.org/wikipedia/commons/6/69/Owen_Chamberlain.jpg"
        val item11 = "https://assets-us-01.kc-usercontent.com/9dd25524-761a-000d-d79f-86a5086d4774/9ec278d3-438c-4185-9d15-862e820de519/reines1.jpg"
        val item12 = "https://www.nobelprize.org/images/lederman-13406-portrait-small.jpg"
        val item13 = "https://www.bnl.gov/science/images/facilities/nsls2.jpg"
        val item14 = "https://history.aip.org/phn/Photos/perl_martin_a2.jpg"
        val item15 = "https://cerncourier.com/wp-content/uploads/2021/10/2021-11-15-webinar-image.jpg"
        val item16 = "https://www.desy.de/sites2009/site_www-desy/content/e141261/e158573/e158935/2017-03-20-XFEL_ger.jpg"
        val item17 = "https://www.indiablooms.com/health_pic/2017/CERN-logo-1497376612.jpg"
        val item18 = "https://cerncourier.com/wp-content/uploads/2021/10/2021-11-15-webinar-image.jpg"
        val item19 = "http://mediaarchive.cern.ch/MediaArchive/Photo/Public/1990/9007366/9007366/9007366-A5-at-72-dpi.jpg"
        val item20 = "https://cerncourier.com/wp-content/uploads/2021/10/2021-11-15-webinar-image.jpg"
        val item21 = "https://cds.cern.ch/images/CERN-PHOTO-201802-030-10/file?size=small"
        listPenemu.add(item1)
        listPenemu.add(item2)
        listPenemu.add(item3)
        listPenemu.add(item4)
        listPenemu.add(item5)
        listPenemu.add(item6)
        listPenemu.add(item7)
        listPenemu.add(item8)
        listPenemu.add(item9)
        listPenemu.add(item10)
        listPenemu.add(item11)
        listPenemu.add(item12)
        listPenemu.add(item13)
        listPenemu.add(item14)
        listPenemu.add(item15)
        listPenemu.add(item16)
        listPenemu.add(item17)
        listPenemu.add(item18)
        listPenemu.add(item19)
        listPenemu.add(item20)
        listPenemu.add(item21)
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