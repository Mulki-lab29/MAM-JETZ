package com.example.mam_jetz.sipeka

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mam_jetz.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi SharedPreferences
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // Navigasi ke Form Input Panen
        binding.btnMenuInputPanen.setOnClickListener {
            startActivity(Intent(this, InputPanenActivity::class.java))
        }

        // Navigasi ke Riwayat Premi
        binding.btnMenuRiwayat.setOnClickListener {
            startActivity(Intent(this, RiwayatPremiActivity::class.java))
        }

        // Navigasi ke Profil Pemanen
        binding.btnMenuProfil.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // --- IMPLEMENTASI LANGKAH 4: FITUR LOGOUT DENGAN ALERTDIALOG ---
        // Asumsikan tombol logout di XML dashboard bernama btnLogout
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar dari aplikasi SIPEKA?")
                .setPositiveButton("Ya") { _, _ ->
                    // Hapus data SharedPreferences
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()

                    // Arahkan kembali ke LoginActivity
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()

        }
        // Navigasi ke Halaman Developer
        binding.btnMenuDeveloper.setOnClickListener {
            startActivity(Intent(this, DeveloperActivity::class.java))
        }
    }
}