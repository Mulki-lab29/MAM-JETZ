package com.example.mam_jetz.sipeka

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mam_jetz.databinding.FragmentRiwayatBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RiwayatFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    // View Binding untuk Fragment
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
        // Inflate layout menggunakan ViewBinding
        _binding = FragmentRiwayatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Event Klik Tombol untuk Membuka RiwayatPremiActivity
        // Pastikan ID tombol di fragment_riwayat.xml adalah btnOpenDetailPremi
        binding.btnOpenDetailPremi.setOnClickListener {
            val intent = Intent(requireContext(), RiwayatPremiActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Bersihkan binding untuk mencegah memory leak
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