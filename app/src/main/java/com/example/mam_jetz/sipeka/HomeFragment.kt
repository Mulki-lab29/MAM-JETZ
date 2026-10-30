package com.example.mam_jetz.sipeka

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mam_jetz.databinding.FragmentHomeBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Event listener navigasi menu
        binding.btnMenuInputPanen.setOnClickListener {
            val intent = Intent(requireContext(), InputPanenActivity::class.java)
            startActivity(intent)
        }

        binding.btnMenuDeveloper.setOnClickListener {
            val intent = Intent(requireContext(), DeveloperActivity::class.java)
            startActivity(intent)
        }

        // Panggil fungsi setup BarChart untuk visualisasi grafik premi
        setupBarChart()
    }

    private fun setupBarChart() {
        // Pastikan ID BarChart di fragment_home.xml adalah barChartPremi
        val barChart: BarChart = binding.barChartPremi

        // 1. Persiapan Data Dummy Tren Premi Panen (Hari 1 s/d 5 dalam Ribuan Rp)
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(1f, 120f)) // Hari 1: Rp 120.000
        entries.add(BarEntry(2f, 180f)) // Hari 2: Rp 180.000
        entries.add(BarEntry(3f, 95f))  // Hari 3: Rp 95.000
        entries.add(BarEntry(4f, 210f)) // Hari 4: Rp 210.000
        entries.add(BarEntry(5f, 160f)) // Hari 5: Rp 160.000

        // 2. Buat BarDataSet dan konfigurasi warna & ukuran teks
        val barDataSet = BarDataSet(entries, "Premi Panen (Ribuan Rp)").apply {
            colors = ColorTemplate.MATERIAL_COLORS.toList()
            valueTextColor = Color.BLACK
            valueTextSize = 12f
        }

        // 3. Masukkan DataSet ke BarData
        val barData = BarData(barDataSet)
        barChart.data = barData

        // 4. Kustomisasi tampilan BarChart
        barChart.description.text = "Grafik Tren Hasil Premi Minggu Ini"
        barChart.animateY(1200) // Animasi batang naik

        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)

        barChart.invalidate() // Re-render chart
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}