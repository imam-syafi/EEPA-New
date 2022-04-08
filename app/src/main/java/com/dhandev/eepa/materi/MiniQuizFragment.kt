package com.dhandev.eepa.materi

import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.dhandev.eepa.databinding.ActivityMateriPengantarBinding
import com.dhandev.eepa.databinding.FragmentMateriBinding
import com.dhandev.eepa.databinding.FragmentMiniQuizBinding

class MiniQuizFragment : Fragment() {

    private var _binding: FragmentMiniQuizBinding? = null
    private val binding get() = _binding!!
    private var nilai = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMiniQuizBinding.inflate(inflater, container, false)
        val root: View = binding.root

        nomorSatu()

        return root
    }

    private fun nomorSatu() {
        with(binding){
            opsi1.visibility = View.VISIBLE
            opsi2.visibility = View.VISIBLE
            opsi3.visibility = View.VISIBLE
            opsi4.visibility = View.VISIBLE
            bintangSkor.visibility = View.GONE
            nilai = 0
            Benar.setText("Benar: $nilai/3")
            soal.setText("Soal ini adalah soal pertama untuk mini quiz, jawaban benar adalah A?")
            opsi1.setText("Opsi 1")
            opsi2.setText("Opsi 2")
            opsi3.setText("Opsi 3")
            opsi4.setText("Opsi 4")
            submit.setText("Cek Jawaban")
            submit.setOnClickListener {
                if (opsi1.isChecked){
                    jawabanBenar()
                    nomorDua()
                } else {
                    jawabanSalah()
                    nomorDua()
                }
            }
        }
    }

    private fun jawabanSalah() {
        Toast.makeText(activity, "Jawaban Anda Salah", Toast.LENGTH_SHORT).show()
        binding.pilihan.clearCheck()
    }

    private fun jawabanBenar() {
        Toast.makeText(activity,"Jawaban Anda Benar!", Toast.LENGTH_SHORT).show()
        nilai++
        binding.Benar.setText("Benar: $nilai/3")
        binding.pilihan.clearCheck()
    }

    private fun nomorDua() {
        with(binding){
            soal.setText("Soal ini adalah soal kedua untuk mini quiz, jawaban benar adalah D?")
            opsi1.setText("Opsi 1 soal kedua")
            opsi2.setText("Opsi 2 soal kedua")
            opsi3.setText("Opsi 3 soal kedua")
            opsi4.setText("Opsi 4 soal kedua")
            submit.setOnClickListener {
                if (opsi4.isChecked){
                    jawabanBenar()
                    nomorTiga()
                } else {
                    jawabanSalah()
                    nomorTiga()
                }
            }
        }
    }

    private fun nomorTiga() {
        with(binding){
            soal.setText("Soal ini adalah soal ketiga untuk mini quiz, jawaban benar adalah B?")
            opsi1.setText("Opsi 1 soal ketiga")
            opsi2.setText("Opsi 2 soal ketiga")
            opsi3.setText("Opsi 3 soal ketiga")
            opsi4.setText("Opsi 4 soal ketiga")
            submit.setOnClickListener {
                if (opsi3.isChecked){
                    jawabanBenar()
                    hasilAkhir()
                } else {
                    jawabanSalah()
                    hasilAkhir()
                }
            }
        }
    }

    private fun hasilAkhir() {
        with(binding){
            opsi1.visibility = View.GONE
            opsi2.visibility = View.GONE
            opsi3.visibility = View.GONE
            opsi4.visibility = View.GONE
            bintangSkor.visibility = View.VISIBLE
            submit.setText("Ulangi?")
            submit.top
            when(nilai){
                3 -> {
                    bintangSkor.setAnimation("anim_benartiga.json")
                    bintangSkor.playAnimation()
                    soal.setText("Luar Biasa!")
                }
                2 -> {
                    bintangSkor.setAnimation("anim_benardua.json")
                    bintangSkor.playAnimation()
                    soal.setText("Keren!")
                }
                1 -> {
                    bintangSkor.setAnimation("anim_benarsatu.json")
                    bintangSkor.playAnimation()
                    soal.setText("Belajar Lagi ya!")
                }
                else -> {
                    bintangSkor.setAnimation("simple_fire.json")
                    bintangSkor.playAnimation()
                    soal.setText("Jangan Menyerah!")
                }
            }
            submit.setOnClickListener {
                nomorSatu()
                //lottie cannot play
            }
        }
    }

    companion object {

    }
}