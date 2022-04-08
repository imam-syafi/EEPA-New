package com.dhandev.eepa

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dhandev.eepa.databinding.ActivityOnBoardingBinding

class onBoarding : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    private lateinit var sharedPred : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPred = this.getSharedPreferences("User", MODE_PRIVATE)

        binding.apply {
            btnMulai.setOnClickListener {
                val username = editTextUser.text.toString()
                if (username.isEmpty()){
                    editTextUser.error = "Nama harus terisi"
                } else {
                    val Editor:SharedPreferences.Editor = sharedPred.edit()
                    Editor.putString("userName", username)
                    Editor.apply()
                }
            }
        }
    }
}