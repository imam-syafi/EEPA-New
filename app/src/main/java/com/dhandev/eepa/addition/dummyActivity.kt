package com.dhandev.eepa.addition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dhandev.eepa.databinding.ActivityDummyBinding

class dummyActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDummyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDummyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.title.text = intent.extras!!.getString("name")!!
        binding.desc.text = intent.extras!!.getString("desc")!!
    }
}