package com.example.mam_jetz.sipeka

import android.content.Intent
import android.os.Bundle
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

        // Menghubungkan tombol di XML dengan logika Kotlin
        binding.btnLogin.setOnClickListener {
            // Pindah dari LoginActivity ke DashboardActivity
            startActivity(Intent(this, DashboardActivity::class.java))
            finish() // Menutup LoginActivity agar tidak bisa di-back
        }
    }
}