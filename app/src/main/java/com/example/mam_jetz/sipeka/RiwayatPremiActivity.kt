package com.example.mam_jetz.sipeka

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.mam_jetz.databinding.ActivityRiwayatPremiBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class RiwayatPremiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRiwayatPremiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatPremiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Event Klik Tombol Back
        binding.btnBack.setOnClickListener {
            finish()
        }

        // Event Klik Tombol Cetak PDF & Share WA
        binding.btnPrintPdf.setOnClickListener {
            generateAndSharePdf()
        }
    }

    private fun generateAndSharePdf() {
        // 1. Inisialisasi Spesifikasi Halaman PDF (A4 Ratio: 792 x 1120)
        val pageHeight = 1120
        val pageWidth = 792
        val pdfDocument = PdfDocument()

        val pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()
        val myPage = pdfDocument.startPage(pageInfo)
        val canvas: Canvas = myPage.canvas

        // 2. Setup Paint / Kuas Pembuat Teks
        val titlePaint = Paint().apply {
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            textSize = 24f
            color = Color.BLACK
            textAlign = Paint.Align.CENTER
        }

        // Menggunakan Color.DKGRAY (bukan DARKGRAY)
        val textPaint = Paint().apply {
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
            textSize = 16f
            color = Color.DKGRAY
        }

        val linePaint = Paint().apply {
            color = Color.BLACK
            strokeWidth = 2f
        }

        // 3. Gambar Konten Slip Gaji ke Canvas PDF
        canvas.drawText("SIPEKA - SLIP GAJI & PREMI PEMANEN", (pageWidth / 2).toFloat(), 80f, titlePaint)

        // Garis Pemisah
        canvas.drawLine(50f, 100f, (pageWidth - 50).toFloat(), 100f, linePaint)

        // Detail Data Panen / Gaji
        canvas.drawText("Nama Pemanen : Mulki Ahmad", 50f, 150f, textPaint)
        canvas.drawText("Divisi       : Blok A / Kebun Sawit", 50f, 180f, textPaint)
        canvas.drawText("Hasil Panen  : 120 TBS", 50f, 210f, textPaint)
        canvas.drawText("Total Premi  : Rp 350.000", 50f, 240f, textPaint)
        canvas.drawText("Status       : Lunas / Terverifikasi", 50f, 270f, textPaint)

        pdfDocument.finishPage(myPage)

        // 4. Simpan File PDF ke Cache Internal App
        val file = File(cacheDir, "Slip_Gaji_SIPEKA.pdf")

        try {
            pdfDocument.writeTo(FileOutputStream(file))
            pdfDocument.close()
            Toast.makeText(this, "PDF Berhasil Dibuat", Toast.LENGTH_SHORT).show()

            // 5. Kirim File PDF ke WhatsApp via FileProvider Intent
            shareToWhatsApp(file)

        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Gagal membuat PDF: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun shareToWhatsApp(file: File) {
        val pdfUri = FileProvider.getUriForFile(
            this,
            "${packageName}.fileprovider",
            file
        )

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, pdfUri)
            putExtra(Intent.EXTRA_TEXT, "Halo, berikut adalah dokumen Slip Gaji Panen SIPEKA.")
            setPackage("com.whatsapp")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        try {
            startActivity(shareIntent)
        } catch (e: Exception) {
            val chooserIntent = Intent.createChooser(shareIntent, "Bagikan Slip Gaji via:")
            startActivity(chooserIntent)
        }
    }
}