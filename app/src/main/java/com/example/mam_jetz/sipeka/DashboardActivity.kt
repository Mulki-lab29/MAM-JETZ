package com.example.mam_jetz.sipeka

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mam_jetz.R

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        // ID disesuaikan dengan activity_dashboard.xml
        val btnInputPanen = findViewById<Button>(R.id.btnMenuInputPanen)
        val btnRiwayat = findViewById<Button>(R.id.btnMenuRiwayat)
        val btnProfile = findViewById<Button>(R.id.btnMenuProfil)

        btnInputPanen.setOnClickListener {
            startActivity(Intent(this, InputPanenActivity::class.java))
        }

        btnRiwayat.setOnClickListener {
            startActivity(Intent(this, RiwayatPremiActivity::class.java))
        }

        btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}