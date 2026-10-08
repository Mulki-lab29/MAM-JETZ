package com.example.mam_jetz.sipeka

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mam_jetz.databinding.ActivityInputPanenBinding

class InputPanenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputPanenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityInputPanenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Jika nanti ada tombol simpan di XML kamu, logikanya di sini:
        // binding.NAMA_ID_TOMBOL.setOnClickListener {
        //     Toast.makeText(this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
        // }
    }
}