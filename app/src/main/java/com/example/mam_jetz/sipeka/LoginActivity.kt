package com.example.mam_jetz.sipeka

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mam_jetz.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // Mengubungkan tombol di XML dengan logika Kotlin
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin?.setOnClickListener {
            // Pindah dari LoginActivity ke DashboardActivity
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish() // Menutup LoginActivity agar tidak bisa di-back
        }
    }
}