package com.example.mam_jetz.sipeka

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mam_jetz.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi SharedPreferences dengan nama "user_pref"
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // Menghubungkan tombol di XML dengan logika Kotlin
        binding.btnLogin.setOnClickListener {
            // Ambil teks dari inputan email/username dan password
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // Validasi sederhana (pastikan komponen id edtEmail dan edtPassword sesuai dengan xml-mu)
            if (email.isNotEmpty() && password.isNotEmpty()) {

                // --- IMPLEMENTASI LANGKAH 3: SIMPAN STATUS LOGIN KE SHAREDREFERENCES ---
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", email)
                editor.apply()

                // Pindah dari LoginActivity ke DashboardActivity
                startActivity(Intent(this, DashboardActivity::class.java))
                finish() // Menutup LoginActivity agar tidak bisa di-back

            } else {
                Toast.makeText(this, "Email dan Password tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}