package com.example.mam_jetz.sipeka

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mam_jetz.databinding.ActivityRiwayatPremiBinding

class RiwayatPremiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRiwayatPremiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRiwayatPremiBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}