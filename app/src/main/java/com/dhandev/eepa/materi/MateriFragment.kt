package com.dhandev.eepa.materi

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.FragmentMateriBinding

class MateriFragment : Fragment() {

    private var _binding: FragmentMateriBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMateriBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.apply {
            arrowBack.setOnClickListener {
                activity?.onBackPressed()
            }

            intro.setOnClickListener {
                val intent = Intent(activity, MateriPengantar::class.java)
                startActivity(intent)
            }
        }
        return root
    }

}