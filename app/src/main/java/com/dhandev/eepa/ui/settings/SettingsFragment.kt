package com.dhandev.eepa.ui.settings

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.FragmentSettingsBinding
import com.dhandev.eepa.onBoarding
import dev.shreyaspatil.MaterialDialog.AbstractDialog
import dev.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog
import dev.shreyaspatil.MaterialDialog.interfaces.DialogInterface

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var sharedPred : SharedPreferences


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

        val username : String?  = sharedPred.getString("userName", "Pengguna")
        binding.userName.text = username

        val baseUrl = "https://docs.google.com/uc?id="
        val avatar : String? = sharedPred.getString("avatarDrawable", "0")
        when(avatar){
            "0" -> Glide.with(this).load(R.drawable.male).circleCrop().into(binding.imageView5)
            "1" -> Glide.with(this).load(R.drawable.female).circleCrop().into(binding.imageView5)
        }

        binding.apply {
            logout.setOnClickListener {

                val BottomSheetDialog = BottomSheetMaterialDialog.Builder(requireActivity())
                    .setTitle("Keluar?")
                    .setMessage("$username, Kamu yakin mau keluar?")
                    .setCancelable(true)
                    .setPositiveButton("Keluar", R.drawable.ic_baseline_done_24){dialog, which ->
                        sharedPred.edit().remove("userName").apply()
                        val intent = Intent(activity, onBoarding::class.java)
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
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}