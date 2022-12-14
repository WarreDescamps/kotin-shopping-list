package com.example.oefstarter.screens.user.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.oefstarter.R
import com.example.oefstarter.databinding.FragmentMainBinding
import com.example.oefstarter.databinding.FragmentUserLoginBinding
import com.example.oefstarter.screens.UserSingelton
import com.example.oefstarter.screens.main.MainViewModel
import com.example.oefstarter.screens.main.MainViewModelFactory

class UserLoginFragment : Fragment() {

    private val viewModel: UserLoginViewModel by viewModels { UserLoginViewModelFactory() }
    private lateinit var binding: FragmentUserLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserLoginBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        viewModel.user.observe(viewLifecycleOwner, Observer {
            it?.let {
                UserSingelton.instance().user = it
                requireView().findNavController().navigate(UserLoginFragmentDirections.actionUserLoginFragmentToMainFragment())
            }
        })
        viewModel.errors.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.etPassword.error = it
            }
        })
        return binding.root
    }
}