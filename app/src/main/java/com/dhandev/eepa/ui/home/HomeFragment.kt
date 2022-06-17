package com.dhandev.eepa.ui.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Color.argb
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.FragmentHomeBinding
import com.dhandev.eepa.materi.*
import com.dhandev.eepa.onBoarding
import com.dhandev.eepa.search.SearchActivity
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

//        binding.introHome.setOnClickListener {
//            val intent = Intent(activity, MateriSubatomik::class.java)
//            startActivity(intent)
//        }
//        binding.more.setOnClickListener(
//            Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_materiFragment)
//        )
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
            "0" -> {
                Glide.with(this).load(R.drawable.male).circleCrop().into(binding.imageView2)
                binding.iconMateri.setImageResource(R.drawable.business_3d_businessman_in_dark_blue_suit_with_phone_looking_straight)
//                binding.horizontalScrollView.background.setColorFilter(Color.parseColor("#ECF3FE"), PorterDuff.Mode.SRC_ATOP)
            }
            "1" -> {
                Glide.with(this).load(R.drawable.female).circleCrop().into(binding.imageView2)
                binding.iconMateri.setImageResource(R.drawable.business_3d_businesswoman_in_red_suit_looking_at_phone)
//                binding.horizontalScrollView.background.setColorFilter(Color.parseColor("#FEECF8"), PorterDuff.Mode.SRC_ATOP)
            }
        }

        val valueSubMateri = sharedPred.getInt("subMateri", 0)

        binding.apply {
            if (valueSubMateri==0){
                mulaiOrLanjut.text = getString(R.string.mulaiBelajar)
                lastRead.text = getString(R.string._1_subatomik)
            } else {
                mulaiOrLanjut.text = getString(R.string.lanjutBelajar)
                when(valueSubMateri){
                    1 -> lastRead.text = getString(R.string._1_subatomik)
                    2 -> lastRead.text = getString(R.string._2_teori_medan_kuantum)
                    3 -> lastRead.text = getString(R.string._3_hadron)
                    4 -> lastRead.text = getString(R.string._4_lepton)
                    5 -> lastRead.text = getString(R.string._5_model_standar)
                    6 -> lastRead.text = getString(R.string._6_perkembangan_terkini)
                }
            }
            horizontalScrollView.setOnClickListener{
                when(valueSubMateri){
                    0, 1 -> startActivity(Intent(activity, MateriSubatomik::class.java))
                    2 -> startActivity(Intent(activity, MateriTMQ::class.java))
                    3 -> startActivity(Intent(activity, MateriHadron::class.java))
                    4 -> startActivity(Intent(activity, MateriLepton::class.java))
                    5 -> startActivity(Intent(activity, MateriStandarModel::class.java))
                    6 -> startActivity(Intent(activity, MateriTerkini::class.java))
                }
            }
            seeAll.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_materiFragment)
            )
            contoh.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_contohActivity)
            )
            latihan.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_latihanActivity)
            )
            flashcard.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_benarSalahActivity)
            )
            referensi.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_referensiActivity)
            )
            lampiran.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_lampiranActivity)
            )
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val carousel : ImageCarousel = view.findViewById(R.id.carousel)
        carousel.registerLifecycle(lifecycle)

        val list = mutableListOf<CarouselItem>()

        val url1 = "https://cache.boston.com/universal/site_graphics/blogs/bigpicture/lhc_08_01/lhc1.jpg"
        val url2 = "https://cdni.russiatoday.com/files/news/1e/57/20/00/eeee-run167675-evt876658967-rphi_copy.si.jpg"
        val url3 = "https://pbs.twimg.com/media/DcDmnE6X4AEwy01?format=jpg&name=medium"

        val desc1 = "Large Hadron Collider (LHC) bertujuan untuk menguji prediksi beberapa teori fisika partikel yang berbeda, termasuk mengukur karakteristik boson Higgs dan mencari kelompok partikel baru yang diprediksi oleh teori supersimetri, juga menyelesaikan pertanyaan tak terjawab dalam fisika. (Gambar dari boston.com)"
        val desc2 = "Peristiwa tumbukan proton-proton pada CMS (Compact Muon Solenoid) di mana 4 elektron energi tinggi (garis hijau dan batang merah) diamati. Peristiwa tersebut menunjukkan karakteristik yang diharapkan dari peluruhan boson Higgs tetapi juga konsisten dengan Model Standar. (Gambar dari cds.cern.ch)"
        val desc3 = "J.J.Thomson penemu partikel elementer pertama melalui eksperimennya menggunakan tabung sinar katoda. (Gambar dari edu.rsc.org)"

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

        binding.editText.setOnClickListener {
            startActivity(Intent(requireActivity(), SearchActivity::class.java))
//            val activityOptionCompat : ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(), binding.editText, "search")
//            val intent = Intent(requireActivity(), SearchActivity::class.java)
//            startActivity(intent, activityOptionCompat.toBundle())
        }
        val scrollDown = activity?.findViewById<TextView>(R.id.tvScroll)
        binding.apply {
            scroll.viewTreeObserver
                .addOnScrollChangedListener(object : ViewTreeObserver.OnScrollChangedListener{
                    override fun onScrollChanged() {
                        if (scroll.getChildAt(0).getBottom()
                            == (scroll.getHeight() + scroll.getScrollY())) {
                            scrollDown?.visibility = View.GONE
                        }
                    }

                })
            scrollDown?.setOnClickListener { scroll.fullScroll(View.FOCUS_DOWN) }
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

    override fun onResume() {
        val valueSubMateri = sharedPred.getInt("subMateri", 0)
        binding.apply {
            if (valueSubMateri==0){
                mulaiOrLanjut.text = getString(R.string.mulaiBelajar)
                lastRead.text = getString(R.string._1_subatomik)
            } else {
                mulaiOrLanjut.text = getString(R.string.lanjutBelajar)
                when(valueSubMateri){
                    1 -> lastRead.text = getString(R.string._1_subatomik)
                    2 -> lastRead.text = getString(R.string._2_teori_medan_kuantum)
                    3 -> lastRead.text = getString(R.string._3_hadron)
                    4 -> lastRead.text = getString(R.string._4_lepton)
                    5 -> lastRead.text = getString(R.string._5_model_standar)
                    6 -> lastRead.text = getString(R.string._6_perkembangan_terkini)
                    7 -> lastRead.text = getString(R.string.garis_waktu_penemuan)
                    8 -> lastRead.text = getString(R.string.penemuan_positron)
                }
            }
            horizontalScrollView.setOnClickListener{
                when(valueSubMateri){
                    0, 1 -> startActivity(Intent(activity, MateriSubatomik::class.java))
                    2 -> startActivity(Intent(activity, MateriTMQ::class.java))
                    3 -> startActivity(Intent(activity, MateriHadron::class.java))
                    4 -> startActivity(Intent(activity, MateriLepton::class.java))
                    5 -> startActivity(Intent(activity, MateriStandarModel::class.java))
                    6 -> startActivity(Intent(activity, MateriTerkini::class.java))
                    7 -> startActivity(Intent(activity, MateriTimeline::class.java))
                    8 -> startActivity(Intent(activity, MateriPositron::class.java))
                }
            }
        }
        super.onResume()
    }
}