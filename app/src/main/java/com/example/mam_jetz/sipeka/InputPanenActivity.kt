package com.example.mam_jetz.sipeka

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mam_jetz.R

class InputPanenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_input_panen)

        // Jika nanti ada tombol simpan di XML kamu, logikanya di sini:
        // val btnSimpan = findViewById<Button>(R.id.NAMA_ID_TOMBOL)
        // btnSimpan?.setOnClickListener {
        //     Toast.makeText(this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
        // }
    }
}