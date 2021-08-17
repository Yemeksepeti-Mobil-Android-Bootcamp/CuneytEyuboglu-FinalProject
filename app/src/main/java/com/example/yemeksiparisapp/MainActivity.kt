package com.example.yemeksiparisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.yemeksiparisapp.ui.home.HomeFragment
import com.example.yemeksiparisapp.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("asasdasdasdasd")
        val fragmentHome: HomeFragment = HomeFragment()
        val fragmentProfile: ProfileFragment = ProfileFragment()
        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_nav_view)

        val navController = findNavController(R.id.nav_host_fragment_container)
        bottomNavView.setupWithNavController(navController)
    }
}