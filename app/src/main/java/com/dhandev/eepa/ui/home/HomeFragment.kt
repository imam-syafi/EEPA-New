package com.dhandev.eepa.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.dhandev.eepa.materi.MateriPengantar
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.FragmentHomeBinding
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class HomeFragment : Fragment() {
    private var _binding :FragmentHomeBinding? = null
    private val binding get() =_binding!!
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