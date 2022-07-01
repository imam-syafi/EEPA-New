package com.dhandev.eepa

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.dhandev.eepa.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navController.addOnDestinationChangedListener{_, destination, _ ->
            when(destination.id){
                R.id.splashFragment -> hideNavScroll()
//                R.id.navigation_news -> binding.tvScroll.visibility = View.GONE
//                R.id.navigation_settings -> binding.tvScroll.visibility = View.GONE
                else -> {
                    navView.visibility = View.VISIBLE
//                    binding.tvScroll.visibility = View.VISIBLE
                }
            }
        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        navView.setupWithNavController(navController)
    }

    private fun hideNavScroll() {
        binding.apply {
            navView.visibility = View.GONE
//            tvScroll.visibility = View.GONE
        }
    }
}