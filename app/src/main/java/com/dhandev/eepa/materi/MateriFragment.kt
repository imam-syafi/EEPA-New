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
            partPengantar.visibility = View.GONE
            partBeyond.visibility = View.GONE
            binding.arrowBack.setOnClickListener {
                activity?.onBackPressed()
            }

            binding.intro.setOnClickListener {
                if (partPengantar.isVisible){
                    partPengantar.visibility = View.GONE
                    intro.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_keyboard_arrow_down_24, 0)
                } else {
                    partPengantar.visibility = View.VISIBLE
                    intro.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_keyboard_arrow_up_24, 0)
                }
            }

            binding.Beyond.setOnClickListener {
                if (partBeyond.isVisible){
                    partBeyond.visibility = View.GONE
                    Beyond.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_keyboard_arrow_down_24, 0)
                } else {
                    partBeyond.visibility = View.VISIBLE
                    Beyond.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_keyboard_arrow_up_24, 0)
                }
            }

            binding.subatomik.setOnClickListener {
                if (partSubatomik.isVisible){
                    partSubatomik.visibility = View.GONE
                    subatomik.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_keyboard_arrow_down_24, 0)
                } else {
                    partSubatomik.visibility = View.VISIBLE
                    subatomik.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_keyboard_arrow_up_24, 0)
                }
            }
        }
        return root
    }

}