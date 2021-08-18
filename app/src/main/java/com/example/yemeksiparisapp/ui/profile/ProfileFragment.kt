package com.example.yemeksiparisapp.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.yemeksiparisapp.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var _binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val token = viewModel.getToken()
        token?.let{
            viewModel.getUserInfo(it).observe(viewLifecycleOwner,{
                it.data?.let{
                    println(it)
                    _binding.profileName.text = it.userinfo.namesurname
                    _binding.profileEmail.text = it.userinfo.email
                    _binding.profileUsername.text = it.userinfo.username
                    Glide.with(_binding.root.context)
                        .load("https://cutewallpaper.org/21/venom-face-logo/face-venom.png")
                        .into(_binding.profileImg)

                }
            })
        }
    }


}