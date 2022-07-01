package com.dhandev.eepa.ui.settings

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.dhandev.eepa.NewOnBoarding
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.FragmentSettingsBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPred : SharedPreferences
    private lateinit var sharedPred2 : SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sharedPred = this.requireActivity().getSharedPreferences("User", AppCompatActivity.MODE_PRIVATE)
        sharedPred2 = this.requireActivity().getSharedPreferences("Tampilan", AppCompatActivity.MODE_PRIVATE)

        val auth = Firebase.auth
        val user = Firebase.auth.currentUser
        val username = user?.displayName
        val profilUrl = user?.photoUrl
        binding.userName.text = username
        Glide.with(this).load(profilUrl).circleCrop().into(binding.imageView5)

        val baseUrl = "https://docs.google.com/uc?id="
//        val avatar : String? = sharedPred.getString("avatarDrawable", "0")
//        when(avatar){
//            "0" -> Glide.with(this).load(R.drawable.male).circleCrop().into(binding.imageView5)
//            "1" -> Glide.with(this).load(R.drawable.female).circleCrop().into(binding.imageView5)
//        }

        binding.apply {
            logout.setOnClickListener {
                val Editor:SharedPreferences.Editor = sharedPred.edit()
                val Editor2:SharedPreferences.Editor = sharedPred2.edit()
                val BottomSheetDialog = BottomSheetMaterialDialog.Builder(requireActivity())
                    .setTitle("Keluar?")
                    .setMessage("$username, Anda yakin mau keluar?")
                    .setCancelable(true)
                    .setPositiveButton("Keluar", R.drawable.ic_baseline_done_24){dialog, which ->
                        auth.signOut()
                        Editor.remove("subMateri")
                        Editor.remove("pedoman")
                        Editor.remove("pedomanSetText")
                        Editor.remove("pedomanGambar")
                        Editor2.remove("gantiLatar")
                        Editor2.remove("tombolTerpilih")
                        Editor2.remove("ukuranBaru")
                        Editor2.remove("tombolUkuranTerpilih")
                        Editor.apply()
                        Editor2.apply()
                        val intent = Intent(activity, NewOnBoarding::class.java)
                        startActivity(intent)
                        activity?.finish() }
                    .setNegativeButton("Batal", R.drawable.ic_baseline_close_24) { dialog, which ->
                        dialog.dismiss()
                    }
                    .setAnimation("logout.json")
                    .build()
                    BottomSheetDialog.show()
                BottomSheetDialog.animationView.scaleType = ImageView.ScaleType.CENTER_INSIDE
            }
            about.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_navigation_settings_to_tentangActivity)
            )
            tampilan.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_navigation_settings_to_tampilanActivity)
            )
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}