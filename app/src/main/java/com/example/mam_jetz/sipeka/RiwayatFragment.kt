package com.example.mam_jetz.sipeka

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.mam_jetz.R
import com.example.mam_jetz.databinding.FragmentRiwayatBinding
import com.example.mam_jetz.sipeka.tutorial.TutorialSipekaActivity
import com.google.android.material.tabs.TabLayoutMediator

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RiwayatFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentRiwayatBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRiwayatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Pasang Adapter Tab ke ViewPager2 langsung di dalam RiwayatFragment
        val tabsAdapter = RiwayatTabsAdapter(this)
        binding.viewPager.adapter = tabsAdapter

        // 2. Hubungkan TabLayout & ViewPager2 (dengan Icon & Badge)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Premi"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.outline_arrow_back_ios_24)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 5
                }
                1 -> {
                    tab.text = "Data Panen"
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                }
                2 -> {
                    tab.text = "Grafik"
                }
            }
        }.attach()

        // 3. Tombol Panduan di pojok kanan atas untuk membuka Onboard Screen kapan saja
        binding.btnTutorial.setOnClickListener {
            startActivity(Intent(requireContext(), TutorialSipekaActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RiwayatFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}