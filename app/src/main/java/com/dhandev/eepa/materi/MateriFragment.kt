package com.dhandev.eepa.materi

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            subatomik.setOnClickListener { startActivity(Intent(activity, MateriSubatomik::class.java)) }
            timeline.setOnClickListener { startActivity(Intent(activity, MateriTimeline::class.java)) }
            TMQ.setOnClickListener {startActivity(Intent(activity, MateriTMQ::class.java))}
            hadron.setOnClickListener { startActivity(Intent(activity, MateriHadron::class.java)) }
            lepton.setOnClickListener { startActivity(Intent(activity, MateriLepton::class.java)) }
            modelStandar.setOnClickListener { startActivity(Intent(activity, MateriStandarModel::class.java)) }
            terkini.setOnClickListener { startActivity(Intent(activity, MateriTerkini::class.java)) }
        }
        return root
    }

}