package com.example.mam_jetz.sipeka

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mam_jetz.databinding.ItemPremiBinding

class PremiAdapter(
    private val premiList: List<PremiModel>,
    private val onItemClick: (PremiModel) -> Unit
) : RecyclerView.Adapter<PremiAdapter.PremiViewHolder>() {

    inner class PremiViewHolder(val binding: ItemPremiBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PremiViewHolder {
        val binding = ItemPremiBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PremiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PremiViewHolder, position: Int) {
        val item = premiList[position]
        with(holder.binding) {
            tvTanggalLahan.text = item.tanggalDanLahan
            tvNominalPremi.text = item.nominalPremi
            tvTotalPanen.text = item.totalPanen

            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int = premiList.size
}