package com.example.mam_jetz.sipeka

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mam_jetz.databinding.ActivityRegisterEmailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RegisterEmailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val emailInput = binding.editTextEmail.text.toString().trim()

            // 1. Validasi: Email tidak boleh kosong
            if (emailInput.isEmpty()) {
                showErrorDialog("Email wajib diisi!")
            }
            // 2. Validasi: Email harus berakhiran @gmail.com
            else if (!emailInput.endsWith("@gmail.com")) {
                showErrorDialog("Email harus menggunakan domain @gmail.com")
            }
            // 3. Jika Valid: Pindah ke RegisterActivity sambil membawa data email
            else {
                val intent = Intent(this, RegisterActivity::class.java)
                intent.putExtra("EXTRA_EMAIL", emailInput)
                startActivity(intent)
            }
        }
    }

    // Fungsi pembantu untuk menampilkan MaterialAlertDialog
    private fun showErrorDialog(message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Peringatan Validasi")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}