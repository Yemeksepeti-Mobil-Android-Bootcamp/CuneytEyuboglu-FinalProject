package com.example.yemeksiparisapp.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.yemeksiparisapp.MainActivity
import com.example.yemeksiparisapp.R
import com.example.yemeksiparisapp.databinding.FragmentLoginBinding
import com.example.yemeksiparisapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var _binding : FragmentLoginBinding
    private val viewModel : LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.btnGoregister.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_registerFragment)
        }

        _binding.btnLogin.setOnClickListener{
            val userIdentity = _binding.editTextName.text.toString()
            val password = _binding.editTextPassword.text.toString()
            viewModel.login(userIdentity, password).observe(viewLifecycleOwner,{
                    when(it.status){
                        Resource.Status.LOADING -> {
                            _binding.loginProgressBar.visibility = View.VISIBLE
                        }
                        Resource.Status.SUCCESS -> {
                            it.data?.let {
                                _binding.loginProgressBar.visibility = View.GONE
                                if(it.status == "fail"){
                                    Toast.makeText(context,"Authentication Failed",Toast.LENGTH_LONG).show()
                                }else{
                                    val intent = Intent(context, MainActivity::class.java)
                                    startActivity(intent)
                                    requireActivity().finish()
                                }
                            }
                        }
                        Resource.Status.ERROR -> {
                            _binding.loginProgressBar.visibility = View.GONE
                            Toast.makeText(context,"Connection Error",Toast.LENGTH_LONG).show()
                        }
                    }
                })

        }
    }

}