package com.dhandev.eepa.asah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dhandev.eepa.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityListBinding
    var listPernyataan = mutableListOf("Soal Pertama", "Soal Kedua", "Soal Ketiga", "Soal Keempat", "Soal Kelima", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val urutanPernyataan = intent.getStringArrayListExtra("urutanPernyataan")

        binding.apply {
            arrowBack.setOnClickListener {
                onBackPressed()
            }
            fab.setOnClickListener {
                listPernyataan.add(5,"Soal Keenam")
                val arrayList = ArrayList(listPernyataan)
                intent.putStringArrayListExtra("urutanPernyataan", arrayList)
                recreate()
            }
            //daftar soal
            soalPertama.text = listPernyataan.get(0)
            soalKedua.text = listPernyataan.get(1)
            soalKetiga.text = listPernyataan.get(2)
            soalKeempat.text = listPernyataan.get(3)
            soalKelima.text = listPernyataan.get(4)
            soalKeenam.text = urutanPernyataan?.get(5)

            if(soalKedua.length() == 0 || soalKedua.equals(""))
            {
                soalKedua.setVisibility(View.GONE);
            } else {
                soalKedua.setVisibility(View.VISIBLE);
            }
        }
    }

    private fun showPernyataan(index : Int) : CharSequence{
        var isiSoal= "Ini soal"
        binding.apply {
            when(listPernyataan?.get(index)){

            }
        }
        return isiSoal
    }
}