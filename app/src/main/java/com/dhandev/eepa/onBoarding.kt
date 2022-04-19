package com.dhandev.eepa

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.dhandev.eepa.databinding.ActivityOnBoardingBinding
import com.dhandev.eepa.ui.home.HomeFragment

class onBoarding : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    private lateinit var sharedPred : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPred = this.getSharedPreferences("User", MODE_PRIVATE)

        binding.apply {

            val baseUrl = "https://docs.google.com/uc?id="
            val avatar1 = "1x_lgALTLLNO-_o8f-wTaPEoFCKd8hEO1"
            val avatar2 = "1xB0U3fV-GxaHWngRQ4wmoE8RMkrKhndp"
            val avatar3 = "1x5soc6Y6CFwMHEQn807he1UTtJBTQ2pa"
            val avatar4 = "1xURR_egSpyeuw-XXqLcTbOEXFhtW-wnA"
            val avatar5 = "1xGRfxpehowAJzCAHKjELvV87lNGB2IY_"
            val avatar6 = "1xpIiiEan6t1Lh7DONNG9U51CNN_DLv6C"
            var usedAvatar = "1x_lgALTLLNO-_o8f-wTaPEoFCKd8hEO1"
            var gender = "0"

            swipeGesture.setAnimation("swipe_left.json")
            swipeGesture.playAnimation()

            Glide.with(this@onBoarding).load(R.drawable.male).circleCrop().into(avatarMale)
            Glide.with(this@onBoarding).load(R.drawable.female).circleCrop().into(avatarFemale)

            val colorMatrix = ColorMatrix()
            colorMatrix.setSaturation(0f)
            val filter = ColorMatrixColorFilter(colorMatrix)
            avatarMale.setColorFilter(filter)
            avatarFemale.setColorFilter(filter)

            avatarMale.setOnClickListener{
                avatarMale.clearColorFilter()
                avatarFemale.setColorFilter(filter)
                gender = "0"

            }
            avatarFemale.setOnClickListener{
                avatarFemale.clearColorFilter()
                avatarMale.setColorFilter(filter)
                gender = "1"
            }

//            Glide.with(this@onBoarding).load(baseUrl+avatar3).circleCrop().into(ivAvatar3)
//            Glide.with(this@onBoarding).load(baseUrl+avatar4).circleCrop().into(ivAvatar4)
//            Glide.with(this@onBoarding).load(baseUrl+avatar5).circleCrop().into(ivAvatar5)
//            Glide.with(this@onBoarding).load(baseUrl+avatar6).circleCrop().into(ivAvatar6)
//
//            ivAvatar1.setOnClickListener {
//                usedAvatar = avatar1
//                changeAvatar(usedAvatar)
//            }
//            ivAvatar2.setOnClickListener {
//                usedAvatar = avatar2
//                changeAvatar(usedAvatar)
//            }
//            ivAvatar3.setOnClickListener {
//                usedAvatar = avatar3
//                changeAvatar(usedAvatar)
//            }
//            ivAvatar4.setOnClickListener {
//                usedAvatar = avatar4
//                changeAvatar(usedAvatar)
//            }
//            ivAvatar5.setOnClickListener {
//                usedAvatar = avatar5
//                changeAvatar(usedAvatar)
//            }
//            ivAvatar6.setOnClickListener {
//                usedAvatar = avatar6
//                changeAvatar(usedAvatar)
//            }

//            Glide.with(this@onBoarding)
//                .load("https://docs.google.com/uc?id=$usedAvatar")
//                .circleCrop()
//                .placeholder(R.drawable.ic_baseline_account_circle_24)
//                .into(avatar)

            btnMulai.setOnClickListener {
                val username = editTextUser.text.toString()
                if (username.isEmpty()){
                    editTextUser.error = "Nama harus terisi"
                } else {
                    val Editor:SharedPreferences.Editor = sharedPred.edit()
                    Editor.putString("userName", username)
                    Editor.putString("avatarDrawable", gender)
                    Editor.apply()
                    startActivity(Intent(this@onBoarding, MainActivity::class.java))
                    finish()
                }
            }
        }
    }

//    private fun changeAvatar(usedAvatar : String) {
//        Glide.with(this@onBoarding)
//            .load("https://docs.google.com/uc?id=$usedAvatar")
//            .circleCrop()
//            .placeholder(R.drawable.ic_baseline_account_circle_24)
//            .into(binding.avatar)
//    }
}