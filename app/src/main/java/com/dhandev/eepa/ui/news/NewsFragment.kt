package com.dhandev.eepa.ui.news

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var dataUrl : String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(NewsViewModel::class.java)

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.nature.setOnClickListener{
            dataUrl = "https://www-nature-com.translate.goog/subjects/particle-physics?error=cookies_not_supported&code=fd9ff9c4-4d59-4984-9fdf-4255a2e89596&_x_tr_sl=en&_x_tr_tl=id&_x_tr_hl=id&_x_tr_pto=wapp"
            customTabs()
        }

        binding.scinews.setOnClickListener{
            dataUrl = "https://www-sciencenews-org.translate.goog/topic/particle-physics?_x_tr_sl=en&_x_tr_tl=id&_x_tr_hl=id&_x_tr_pto=wapp"
            customTabs()
        }

        binding.newsci.setOnClickListener {
            dataUrl = "https://www-newscientist-com.translate.goog/article-topic/particle-physics/?_x_tr_sl=en&_x_tr_tl=id&_x_tr_hl=id&_x_tr_pto=wapp"
            customTabs()
        }

        binding.scitech.setOnClickListener {
            dataUrl = "https://scitechdaily-com.translate.goog/tag/particle-physics/?_x_tr_sl=en&_x_tr_tl=id&_x_tr_hl=id&_x_tr_pto=wapp"
            customTabs()
        }

        binding.phyorg.setOnClickListener {
            dataUrl = "https://phys-org.translate.goog/tags/particle+physics/?_x_tr_sl=en&_x_tr_tl=id&_x_tr_hl=id&_x_tr_pto=wapp"
            customTabs()
        }

        return root
    }

    private fun customTabs() {
        val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
        val colorInt : Int = Color.parseColor("#309AE3")
        var defaulColor : CustomTabColorSchemeParams = CustomTabColorSchemeParams.Builder().setToolbarColor(colorInt).build()
        builder.setDefaultColorSchemeParams(defaulColor)
        this.activity?.let { startAnim -> builder.setStartAnimations(startAnim, androidx.navigation.ui.R.anim.nav_default_enter_anim, androidx.navigation.ui.R.anim.nav_default_exit_anim) }
        this.activity?.let { exitAnim -> builder.setExitAnimations(exitAnim, androidx.navigation.ui.R.anim.nav_default_pop_enter_anim, androidx.navigation.ui.R.anim.nav_default_pop_exit_anim) }
        val customTabsIntent: CustomTabsIntent = builder.build()
        this.activity?.let { it1 -> customTabsIntent.launchUrl(it1, Uri.parse(dataUrl)) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}