package com.example.practica2

import ViewPagerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practica2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayout = binding.studentTabs
        val viewPager = binding.studentInfoViewPager2

        val dataClasses = DataClasses
        val emails = dataClasses.students.map { it.email }

        val adapter = ViewPagerAdapter(this, dataClasses)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val username = emails[position].split("@")[0]
            tab.text = username
        }.attach()
    }
}
