package com.dhandev.eepa.ui.home

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.dhandev.eepa.materi.MateriPengantar
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.FragmentHomeBinding
import com.dhandev.eepa.onBoarding
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class HomeFragment : Fragment() {
    private var _binding :FragmentHomeBinding? = null
    private val binding get() =_binding!!
    private lateinit var sharedPred : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.more.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_materiFragment)
        )
        binding.seeAll.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_materiFragment)
        )
        binding.introHome.setOnClickListener {
            val intent = Intent(activity, MateriPengantar::class.java)
            startActivity(intent)
        }
        binding.contoh.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_contohActivity)
        )

        sharedPred = this.requireActivity().getSharedPreferences("User", AppCompatActivity.MODE_PRIVATE)

        val username : String?  = sharedPred.getString("userName", null)

        if (username != null){
            binding.userName.text = username

        } else {
            startActivity(Intent(activity, onBoarding::class.java))
            activity?.finish()
        }
        val baseUrl = "https://docs.google.com/uc?id="
        val avatar : String? = sharedPred.getString("avatarUrl", null)
        Glide.with(this).load(baseUrl+avatar).circleCrop().into(binding.imageView2)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val carousel : ImageCarousel = view.findViewById(R.id.carousel)
        carousel.registerLifecycle(lifecycle)

        val list = mutableListOf<CarouselItem>()


        list.add(
            CarouselItem(
                imageDrawable = R.drawable.materiscroll1,
            )
        )

        list.add(
            CarouselItem(
                imageDrawable = R.drawable.materiscroll2,
            )
        )

        list.add(
            CarouselItem(
                imageDrawable = R.drawable.materiscroll3,
            )
        )
        carousel.setData(list)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}