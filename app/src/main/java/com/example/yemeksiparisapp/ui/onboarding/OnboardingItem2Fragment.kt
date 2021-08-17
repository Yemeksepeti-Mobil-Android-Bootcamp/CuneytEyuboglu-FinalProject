package com.example.yemeksiparisapp.ui.onboarding

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.yemeksiparisapp.AuthActivity
import com.example.yemeksiparisapp.MainActivity
import com.example.yemeksiparisapp.R
import com.example.yemeksiparisapp.StartActivity
import com.example.yemeksiparisapp.databinding.FragmentOnboardingItem2Binding

class OnboardingItem2Fragment: Fragment() {

    private lateinit var _binding:FragmentOnboardingItem2Binding
    var sharedPreferences: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            val nextBtn = it.findViewById<AppCompatButton>(R.id.next_btn)
            nextBtn.visibility = View.GONE
        }
        _binding = FragmentOnboardingItem2Binding.inflate(inflater,container,false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.startBtn.setOnClickListener{
            savePrefData()
            val intent = Intent(activity, AuthActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

    }

    private fun savePrefData() {
        sharedPreferences = requireActivity().getSharedPreferences("pref",
            AppCompatActivity.MODE_PRIVATE
        )
        val editor = sharedPreferences!!.edit()
        editor.putBoolean("isFirstTimeRun", true)
        editor.apply()
    }
}