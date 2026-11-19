package com.example.mam_jetz.sipeka

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class RiwayatTabsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabPremiFragment()
            1 -> TabPanenFragment()
            2 -> TabGrafikFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}