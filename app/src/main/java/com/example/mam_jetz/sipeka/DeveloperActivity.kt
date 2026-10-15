package com.example.mam_jetz.sipeka

import android.os.Bundle
import android.widget.ImageButton // sesuaikan jika tombol kembali Anda Button biasa atau ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mam_jetz.R

class DeveloperActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Memuat layout secara langsung tanpa View Binding untuk bypass error
        setContentView(R.layout.activity_developer)

        // Aksi tombol kembali (pastikan id tombol di xml adalah btnBack)
        findViewById<android.view.View>(R.id.btnBack)?.setOnClickListener {
            finish()
        }
    }
}