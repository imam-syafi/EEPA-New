package com.dhandev.eepa.materi

import android.content.res.Resources
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import android.util.TypedValue
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.paris.R2.attr.colorPrimary
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityMateriTimelineBinding
import com.dhandev.eepa.helper.BaseAdapter
import com.dhandev.eepa.helper.customTab
import com.lriccardo.timelineview.TimelineDecorator
import me.saket.bettermovementmethod.BetterLinkMovementMethod

class MateriTimeline : AppCompatActivity() {
    private lateinit var binding: ActivityMateriTimelineBinding
    var listItem = mutableListOf<String>()
    var listDesc = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMateriTimelineBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        val desc1 = "Ditemukan oleh J.J. Thomson melalui eksperimen tabung sinar katoda"
        val desc2 = "Dicetuskan oleh Albert Einstein hingga akhirnya dipopulerkan dengan nama foton oleh Gilbert N. Lewis pada 1926"
        val desc3 = "Eksperimen dengan menggunakan lembaran emas di mana Rutherford berhasil menemukan adanya inti atom yang sangat kecil namun sangat berat."
        val desc4 = "Ditemukan oleh James Chadwick pada eksperimen di mana Ia membombardir Berilium dengan partikel alfa dari peluruhan radioaktif alami Polonium."
        val desc5 = "Ditemukan oleh Carl D. Anderson secara tidak sengaja pada eksperimen ruang kabut, di mana terdapat lintasan partikel yang menunjukkan ciri-ciri elektron namun dengan muatan positif."
        val desc6 = "Ditemukan oleh Seth Neddermeyer dkk. pada eksperimen pengukuran sinar kosmis menggunakan ruang kabut."
        val desc7 = "Ditemukan oleh Kelompok peneliti yang dipimpin C.F.Powell, sebelumnya partikel ini sudah siprediksi oleh Hideki Yukawa pada 1935"
        val desc8 = "George Dixon dan Clifford C.C. menemukan kaon sebagai partikel strange pertama yang ditemukan"
        val desc9 = "V.D. Hopper dan S.Biswas menemukan partikel ini dari hasil tumbukan proton dan neutron"
        val desc10 = "Owen Chamberlain dkk. menemukan antiproton dengan cara memborbardir target tembaga dengan proton berenergi tinggi dari proton synchrotron di University of California, Berkeley."
        val desc11 = "Frederick R. dan Clyde C. membuktikan keberadaan partikel ini, setelah sebelumnya diprediksi keberadaannya oleh Wolfgang Pauli pada 1930. Penemuan ini juga yang pertama untuk partikel neutrino"
        val desc12 = "Ditemukan oleh grup peneliti yang dipimpin oleh Leon Lederman setelah menyadari bahwa partikel yang mereka temukan berbeda dengan neutrino sebelumnya"
        val desc13 = "Ditemukan melalui eksperimen di Brookhaven National Laboratory"
        val desc14 = "Martine Lewis menemukan partikel ini saat mengobservasi peluruhan partikel yang menjadi muon dan elektron di Stanford Linear Accelerator Centre"
        val desc15 = "Setelah diprediksi oleh Kobayashi dan Maskawa pada 1973, partikel ini berhasil ditemukan di Fermilab"
        val desc16 = "Ditemukan secara tidak langsung saat peristiwa three-jet di DESY (Deutsches Elektronen-Synchrotron)"
        val desc17 = "Ditemukan oleh Carlo Rubbia dan Simon di CERN"
        val desc18 = "Ditemukan di Fermilab"
        val desc19 = "Diproduksi dan diukur pada eksperimen LEAR (Low Energy Antiproton Ring) di CERN"
        val desc20 = "Pertama kali diamati secara langsung di Fermilab"
        val desc21 = "Ditemukan pada eksperimen menggunakan Compact Muon Solenoid dan ATLAS di Large Hadron Collider di CERN"
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