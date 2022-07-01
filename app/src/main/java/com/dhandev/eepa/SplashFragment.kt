package com.dhandev.eepa

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dhandev.eepa.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private var _binding : FragmentSplashBinding? = null
    private val binding get() =_binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Handler(Looper.myLooper()!!).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_navigation_home)
        }, 1600)

        val animation : ObjectAnimator = ObjectAnimator.ofInt(binding.progressCircular, "progress", 0, 110)
        animation.setDuration(1500)
        animation.interpolator
        animation.start()
        val appName = getString(R.string.app_name)
        binding.version.text ="$appName \n${BuildConfig.VERSION_NAME}"
        Glide.with(this)
            .load(R.drawable.logobarubulat)
            .circleCrop()
            .into(binding.imageView3)
        return root
    }

}