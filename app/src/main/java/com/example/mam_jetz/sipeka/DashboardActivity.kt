package com.example.mam_jetz.sipeka

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mam_jetz.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMenuInputPanen.setOnClickListener {
            startActivity(Intent(this, InputPanenActivity::class.java))
        }

        binding.btnMenuRiwayat.setOnClickListener {
            startActivity(Intent(this, RiwayatPremiActivity::class.java))
        }

        binding.btnMenuProfil.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}