package com.example.mam_jetz.sipeka

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mam_jetz.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Event listener saat tombol Logout diklik 🔴
        binding.btnLogout.setOnClickListener {
            // 1. Ubah status isLogin menjadi false di SharedPreferences
            val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putBoolean("isLogin", false)
            editor.apply()

            // 2. Pindah kembali ke LoginActivity
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)

            // 3. Tutup BaseActivity agar tidak bisa kembali dengan tombol back
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}