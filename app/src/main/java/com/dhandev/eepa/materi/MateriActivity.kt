package com.dhandev.eepa.materi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dhandev.eepa.databinding.ActivityMateriBinding

class MateriActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMateriBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMateriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            arrowBack.setOnClickListener {
                onBackPressed()
            }
            subatomik.setOnClickListener { startActivity(Intent(this@MateriActivity, MateriSubatomik::class.java)) }
            TMQ.setOnClickListener {startActivity(Intent(this@MateriActivity, MateriTMQ::class.java))}
            hadron.setOnClickListener { startActivity(Intent(this@MateriActivity, MateriHadron::class.java)) }
            lepton.setOnClickListener { startActivity(Intent(this@MateriActivity, MateriLepton::class.java)) }
            modelStandar.setOnClickListener { startActivity(Intent(this@MateriActivity, MateriStandarModel::class.java)) }
            terkini.setOnClickListener { startActivity(Intent(this@MateriActivity, MateriTerkini::class.java)) }
        }
    }

}