package com.dhandev.eepa

import android.animation.Animator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.dhandev.eepa.databinding.ActivityNewOnBoardingBinding
import com.dhandev.eepa.helper.ViewPagerAdapter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class NewOnBoarding : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var imageList: MutableList<Int>
    private lateinit var progress: MutableList<Int>
    private lateinit var judulList: MutableList<String>
    private lateinit var descList: MutableList<String>
    private lateinit var binding: ActivityNewOnBoardingBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = findViewById(R.id.viewSlide)
        imageList = ArrayList()
        imageList.add(R.drawable.casual_life_3d_boy_sitting_at_the_desk_doing_exam_1)
        imageList.add(R.drawable.atom)
        imageList.add(R.drawable.atom_deep)
        imageList.add(R.drawable.galaxy)
        imageList.add(R.drawable.casual_life_3d_20)

        judulList = ArrayList()
        judulList.add(getString(R.string.cari_tahu))
        judulList.add(getString(R.string.di_dalam_atom))
        judulList.add(getString(R.string.tahukah_anda))
        judulList.add(getString(R.string.sangat_bermanfaat))
        judulList.add(getString(R.string.penasaran))

        descList = ArrayList()
        descList.add(getString(R.string.tahukah_anda_partikel_apa_n_yang_lebih_mendasar_dari_atom))
        descList.add(getString(R.string.proton_neutron_dan_elektron_mungkin_nmenjadi_yang_pertama_terbesit_ndalam_pikiran_anda))
        descList.add(getString(R.string.ternyata_masih_ada_partikel_yang_lebih_nmendasar_yang_menyusun_partikel_tadi))
        descList.add(getString(R.string.dengan_mempelajari_ini_kita_bisa_nmengetahui_asal_usul_alam_semesta_lo))
        descList.add(getString(R.string.yuk_pelajari))

        progress = ArrayList()
        progress.add(20)
        progress.add(40)
        progress.add(60)
        progress.add(80)
        progress.add(100)

        viewPagerAdapter = ViewPagerAdapter(this, imageList, judulList, descList, progress)

        viewPager.adapter = viewPagerAdapter

        binding.apply {
            swipeGesture.setAnimation("swipe_left.json")
            swipeGesture.playAnimation()
            swipeGesture.addAnimatorListener(object: Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator?) {
                    Log.e("Animation:","start");
                    swipeGesture.setVisibility(View.VISIBLE);
                }

                override fun onAnimationEnd(p0: Animator?) {
                    swipeGesture.visibility = View.INVISIBLE
                }

                override fun onAnimationCancel(p0: Animator?) {
                    Log.e("Animation:","cancel");
                }

                override fun onAnimationRepeat(p0: Animator?) {
                    Log.e("Animation:","repeat");
                }
            })

            // Configure Google Sign In
            val gso = GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)) //tidak apa2, tetap bisa jalan
                .requestEmail()
                .build()
            googleSignInClient = GoogleSignIn.getClient(this@NewOnBoarding, gso)
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
}