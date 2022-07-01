package com.dhandev.eepa

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dhandev.eepa.databinding.ActivityOnBoardingBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class onBoarding : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    private lateinit var sharedPred : SharedPreferences
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    var gender = "3"

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

            editTextUser.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    mulai()
                    true
                } else {
                    false
                }
            }
            //never seen, cuz the view is not activated
            btnMulai.setOnClickListener {
                mulai()
            }

            // Configure Google Sign In
            val gso = GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)) //tidak apa2, tetap bisa jalan
                .requestEmail()
                .build()
            googleSignInClient = GoogleSignIn.getClient(this@onBoarding, gso)
            // Initialize Firebase Auth
            auth = Firebase.auth

            btnSkip.setOnClickListener {
                loginWithGoogle()
            }
        }
    }

    private fun loginWithGoogle() {
        showLoading(true)
        val signInIntent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null){
            showLoading(false)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


    private fun mulai() {
        binding.apply {
            val username = editTextUser.text.toString()
            if (username.isEmpty()){
                editTextUser.error = "Nama harus terisi"
            } else if (gender == "3") {
                Toast.makeText(this@onBoarding, "Silahkan pilih avatar", Toast.LENGTH_SHORT).show()
            }
            else {
                val Editor:SharedPreferences.Editor = sharedPred.edit()
                Editor.putString("userName", username)
                Editor.putString("avatarDrawable", gender)
                Editor.apply()
                startActivity(Intent(this@onBoarding, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun showLoading(b: Boolean) {
        if (b) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        private const val TAG = "onBoarding"
    }
//    private fun changeAvatar(usedAvatar : String) {
//        Glide.with(this@onBoarding)
//            .load("https://docs.google.com/uc?id=$usedAvatar")
//            .circleCrop()
//            .placeholder(R.drawable.ic_baseline_account_circle_24)
//            .into(binding.avatar)
//    }
}