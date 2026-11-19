package com.example.mam_jetz.sipeka.tutorial

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mam_jetz.databinding.ActivityTutorialSipekaBinding
import com.example.mam_jetz.sipeka.BaseActivity

class TutorialSipekaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialSipekaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialSipekaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentsList = listOf(
            Tutorial1Fragment(),
            Tutorial2Fragment(),
            Tutorial3Fragment()
        )

        val adapter = TutorialFragmentAdapter(this, fragmentsList)
        binding.tutorialViewPager.adapter = adapter

        // Hubungkan DotsIndicator ke ViewPager2
        binding.dotIndicator.attachTo(binding.tutorialViewPager)

        binding.btnSelesaiTutorial.setOnClickListener {
            val intent = Intent(this, BaseActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}