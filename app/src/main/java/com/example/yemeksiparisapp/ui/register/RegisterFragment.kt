package com.example.yemeksiparisapp.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.yemeksiparisapp.MainActivity
import com.example.yemeksiparisapp.databinding.FragmentRegisterBinding
import com.example.yemeksiparisapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var _binding:FragmentRegisterBinding
    private val viewModel:RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.btnCreateAccount.setOnClickListener{
            val namesurname = _binding.RegisterEditTextName.text.toString()
            val username = _binding.RegisterEditTextName.text.toString()
            val email = _binding.RegisterEditTextName.text.toString()
            val password = _binding.RegisterEditTextName.text.toString()
            viewModel.register(email,namesurname,password,username)
                .observe(viewLifecycleOwner, {
                    when(it.status){
                        Resource.Status.LOADING -> {
                            println("loading...")
                        }
                        Resource.Status.SUCCESS -> {
                            it.data?.let {
                                if(it.status == "success") {
                                    viewModel.saveToken(it.token)
                                    val intent = Intent(context, MainActivity::class.java)
                                    startActivity(intent)
                                    requireActivity().finish()
                                }else{
                                    Toast.makeText(context,"Register Error", Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                        Resource.Status.ERROR -> {
                            Toast.makeText(context,"Connection Error", Toast.LENGTH_LONG).show()
                        }

                    }
                })
        }
    }

}