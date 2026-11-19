package com.example.mam_jetz.sipeka

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.mam_jetz.databinding.FragmentTabPremiBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import androidx.recyclerview.widget.LinearLayoutManager

class TabPremiFragment : Fragment() {

    private var _binding: FragmentTabPremiBinding? = null
    private val binding get() = _binding!!

    private val premiList = listOf(
        PremiModel("14 JULI 2026 • Lahan Sulit", "Rp 1.780.000", "Total Panen: 5.000,00 Kg"),
        PremiModel("12 JULI 2026 • Lahan Flat", "Rp 1.190.000", "Total Panen: 3.500,00 Kg"),
        PremiModel("10 JULI 2026 • Lahan Flat", "Rp 600.000", "Total Panen: 1.500,00 Kg"),
        PremiModel("08 JULI 2026 • Lahan Sulit", "Rp 1.424.000", "Total Panen: 4.000,00 Kg"),
        PremiModel("05 JULI 2026 • Lahan Flat", "Rp 850.000", "Total Panen: 2.500,00 Kg")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabPremiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup RecyclerView (Modul 11)
        val adapter = PremiAdapter(premiList) { selectedItem ->
            Toast.makeText(
                requireContext(),
                "Slip ${selectedItem.tanggalDanLahan}: ${selectedItem.nominalPremi}",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvPremi.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }

        // Event Klik Tombol Cetak PDF & Share WA (Modul 9)
        binding.btnPrintPdf.setOnClickListener {
            generateAndSharePdf()
        }
    }

    private fun generateAndSharePdf() {
        val pageHeight = 1120
        val pageWidth = 792
        val pdfDocument = PdfDocument()

        val pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()
        val myPage = pdfDocument.startPage(pageInfo)
        val canvas: Canvas = myPage.canvas

        val titlePaint = Paint().apply {
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            textSize = 24f
            color = Color.BLACK
            textAlign = Paint.Align.CENTER
        }

        val textPaint = Paint().apply {
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
            textSize = 16f
            color = Color.DKGRAY
        }

        val linePaint = Paint().apply {
            color = Color.BLACK
            strokeWidth = 2f
        }

        canvas.drawText("SIPEKA - SLIP GAJI & PREMI PEMANEN", (pageWidth / 2).toFloat(), 80f, titlePaint)
        canvas.drawLine(50f, 100f, (pageWidth - 50).toFloat(), 100f, linePaint)

        canvas.drawText("Nama Pemanen : Mulki Ahmad", 50f, 150f, textPaint)
        canvas.drawText("Divisi       : Blok A / Kebun Sawit", 50f, 180f, textPaint)
        canvas.drawText("Hasil Panen  : 120 TBS", 50f, 210f, textPaint)
        canvas.drawText("Total Premi  : Rp 350.000", 50f, 240f, textPaint)
        canvas.drawText("Status       : Lunas / Terverifikasi", 50f, 270f, textPaint)

        pdfDocument.finishPage(myPage)

        // Gunakan requireContext().cacheDir di dalam Fragment
        val file = File(requireContext().cacheDir, "Slip_Gaji_SIPEKA.pdf")

        try {
            pdfDocument.writeTo(FileOutputStream(file))
            pdfDocument.close()
            Toast.makeText(requireContext(), "PDF Berhasil Dibuat", Toast.LENGTH_SHORT).show()

            shareToWhatsApp(file)

        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Gagal membuat PDF: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun shareToWhatsApp(file: File) {
        val context = requireContext()
        val pdfUri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}