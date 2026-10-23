package com.example.mam_jetz.sipeka

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mam_jetz.databinding.ActivityRegisterBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menerima data email dari RegisterEmailActivity & menguncinya
        val emailFromIntent = intent.getStringExtra("EXTRA_EMAIL")
        binding.editTextEmail.setText(emailFromIntent)
        binding.editTextEmail.isEnabled = false

        binding.btnRegister.setOnClickListener {
            val nama = binding.editTextNama.text.toString().trim()
            val email = binding.editTextEmail.text.toString().trim()
            val username = binding.editTextUsername.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            // 1. Validasi: Semua field wajib diisi
            if (nama.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                showDialog("Peringatan", "Semua field wajib diisi!")
            }
            // 2. Validasi: Password minimal 6 karakter
            else if (password.length < 6) {
                showDialog("Peringatan", "Password minimal harus 6 karakter!")
            }
            // 3. Validasi: Username tidak boleh mengandung spasi
            else if (username.contains(" ")) {
                showDialog("Peringatan", "Username tidak boleh mengandung spasi!")
            }
            // 4. Jika Valid: Simpan ke SharedPreferences
            else {
                val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("registered_nama", nama)
                editor.putString("registered_email", email)
                editor.putString("registered_username", username)
                editor.putString("registered_password", password)
                editor.apply()

                // Tampilkan pesan sukses & tutup halaman registrasi
                MaterialAlertDialogBuilder(this)
                    .setTitle("Sukses")
                    .setMessage("Registrasi Berhasil!")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                        finish() // Kembali ke halaman Login
                    }
                    .show()
            }
        }
    }

    private fun showDialog(title: String, message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}