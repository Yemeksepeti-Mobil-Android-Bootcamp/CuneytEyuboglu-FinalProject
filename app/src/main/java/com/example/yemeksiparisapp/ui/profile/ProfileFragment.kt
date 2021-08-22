package com.example.yemeksiparisapp.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.yemeksiparisapp.AuthActivity
import com.example.yemeksiparisapp.R
import com.example.yemeksiparisapp.databinding.FragmentProfileBinding
import com.example.yemeksiparisapp.ui.adapters.PreviousOrdersAdapter
import com.example.yemeksiparisapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var _binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()
    private var PreviousOrdersRvAdapter: PreviousOrdersAdapter = PreviousOrdersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.profileRv.adapter = PreviousOrdersRvAdapter
        val token = viewModel.getToken()
        token?.let{
            signOutButtonListener()
            getUserInfo(it)
            clearOrdersButtonListener(it)
        }
    }

    private fun signOutButtonListener() {
        _binding.signoutBtn.setOnClickListener{
            viewModel.signOut()
            activity?.let{
                val intent = Intent(it, AuthActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                startActivity(intent)
                it.finish()
            }
        }
    }

    private fun clearOrdersButtonListener(token: String) {
        _binding.clearOrdersBtn.setOnClickListener{
            viewModel.clearOrders(token).observe(viewLifecycleOwner,{
                when(it.status){
                    Resource.Status.LOADING->{
                        Toast.makeText(requireContext(),"Clearing Order History...",Toast.LENGTH_SHORT)
                            .show()
                    }
                    Resource.Status.SUCCESS->{
                        PreviousOrdersRvAdapter.orderList = listOf()
                        PreviousOrdersRvAdapter.notifyDataSetChanged()
                    }
                }
            })
        }
    }

    private fun getUserInfo(token: String) {
        viewModel.getUserInfo(token).observe(viewLifecycleOwner,{
            when(it.status){
                Resource.Status.LOADING -> {}
                Resource.Status.SUCCESS -> {
                    it.data?.let{
                        _binding.profileLoadingBar.visibility = View.INVISIBLE
                        _binding.profileName.text = it.userinfo.namesurname
                        _binding.profileEmail.text = it.userinfo.email
                        _binding.profileUsername.text = it.userinfo.username
                        PreviousOrdersRvAdapter.orderList = it.userinfo.previousOrders
                        PreviousOrdersRvAdapter.notifyDataSetChanged()
                    }
                }
                Resource.Status.ERROR -> {}
            }

        })
    }
}