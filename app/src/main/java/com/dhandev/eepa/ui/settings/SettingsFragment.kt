package com.dhandev.eepa.ui.settings

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dhandev.eepa.databinding.FragmentSettingsBinding
import com.dhandev.eepa.onBoarding

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

        binding.apply {
            logout.setOnClickListener {
                sharedPred.edit().remove("userName").apply()
                val intent = Intent(activity, onBoarding::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }

        val username : String?  = sharedPred.getString("userName", "Pengguna")
        binding.userName.text = username

        val baseUrl = "https://docs.google.com/uc?id="
        val avatar : String? = sharedPred.getString("avatarUrl", null)
        Glide.with(this).load(baseUrl+avatar).circleCrop().into(binding.imageView5)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}