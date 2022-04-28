package com.dhandev.eepa.ui.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.dhandev.eepa.materi.MateriPengantar
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.FragmentHomeBinding
import com.dhandev.eepa.onBoarding
import com.dhandev.eepa.ui.imageViewer.ImageViewerActivity
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
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
        binding.latihan.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_latihanActivity)
        )
        binding.flashcard.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_benarSalahActivity)
        )
        binding.referensi.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_referensiActivity)
        )

        sharedPred = this.requireActivity().getSharedPreferences("User", AppCompatActivity.MODE_PRIVATE)

        val username : String?  = sharedPred.getString("userName", null)

        if (username != null){
            binding.userName.text = username

        } else {
            startActivity(Intent(activity, onBoarding::class.java))
            activity?.finish()
        }
//        val baseUrl = "https://docs.google.com/uc?id="
//        val avatar : String? = sharedPred.getString("avatarUrl", null)
//        Glide.with(this).load(baseUrl+avatar).circleCrop().into(binding.imageView2)
        val avatar = sharedPred.getString("avatarDrawable", "0")
        when(avatar){
            "0" -> Glide.with(this).load(R.drawable.male).circleCrop().into(binding.imageView2)
            "1" -> Glide.with(this).load(R.drawable.female).circleCrop().into(binding.imageView2)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val carousel : ImageCarousel = view.findViewById(R.id.carousel)
        carousel.registerLifecycle(lifecycle)

        val list = mutableListOf<CarouselItem>()

        val url1 = "https://cache.boston.com/universal/site_graphics/blogs/bigpicture/lhc_08_01/lhc1.jpg"
        val url2 = "https://docs.google.com/uc?id=1yahQeRW9WFVlC_Aq8lLtd0P07TQKMbms"
        val url3 = "https://pbs.twimg.com/media/DcDmnE6X4AEwy01?format=jpg&name=medium"

        val desc1 = "Large Hadron Collider (LHC)"
        val desc2 = "A collision between two proton in the CMS detector"
        val desc3 = "J.J.Thomson penemu partikel elementer pertama"

        list.add(
            CarouselItem(
                imageUrl = url1,
                caption = desc1
                )
        )

        list.add(
            CarouselItem(
                imageUrl = url2,
                caption = desc2
            )
        )

        list.add(
            CarouselItem(
                imageUrl = url3,
                caption = desc3
            )
        )
        carousel.setData(list)

        carousel.carouselListener = object : CarouselListener{
            override fun onClick(position: Int, carouselItem: CarouselItem) {
                val Editor:SharedPreferences.Editor = sharedPred.edit()
                when(position){
                    0 -> Editor.putString("urlHead", url1).putString("desc", desc1).apply()
                    1 -> Editor.putString("urlHead", url2).putString("desc", desc2).apply()
                    2 -> Editor.putString("urlHead", url3).putString("desc", desc3).apply()
                }
                startActivity(Intent(activity, ImageViewerActivity::class.java))
            }

            override fun onLongClick(position: Int, carouselItem: CarouselItem) {
                Toast.makeText(
                    activity,
                    "You long clicked at position ${position + 1}.",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    //langsung akhiri activity ketika tekan kembali, bahkan setelah bernavigasi ke fragment news maupun settings
    //sebelumnya akan terjadi penumpukan fragment
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    activity?.finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}