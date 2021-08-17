package com.example.yemeksiparisapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.yemeksiparisapp.databinding.ActivityStartBinding
import com.example.yemeksiparisapp.ui.adapters.PagerAdapter

class StartActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityStartBinding
    lateinit var viewPager: ViewPager2
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        viewPager = _binding.onboardingViewPager
        val dotsIndicator = _binding.dotsIndicator
        val adapter = PagerAdapter(this)
        viewPager.adapter = adapter
        dotsIndicator.setViewPager2(viewPager)

        if(restorePrefData()){
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }


    private fun restorePrefData() : Boolean{
        sharedPreferences = this.getSharedPreferences("pref",
            MODE_PRIVATE
        )
        return sharedPreferences!!.getBoolean("isFirstTimeRun", false)
    }
}