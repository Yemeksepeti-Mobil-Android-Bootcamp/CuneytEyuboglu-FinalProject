package com.example.yemeksiparisapp.ui.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.yemeksiparisapp.ui.onboarding.AppLogoFragment
import com.example.yemeksiparisapp.ui.onboarding.OnboardingItem1Fragment
import com.example.yemeksiparisapp.ui.onboarding.OnboardingItem2Fragment

private const val FRAGMENT_COUNT = 3

class PagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = FRAGMENT_COUNT
    val context = activity
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AppLogoFragment()
            1 -> OnboardingItem1Fragment()
            2 -> OnboardingItem2Fragment()
            else -> AppLogoFragment()
        }

    }
}