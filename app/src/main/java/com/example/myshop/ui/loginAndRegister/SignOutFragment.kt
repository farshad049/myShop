package com.example.myshop.ui.loginAndRegister

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myshop.BaseFragment
import com.example.myshop.R
import com.example.myshop.databinding.FragmenrSignoutBinding


class SignOutFragment:BaseFragment(R.layout.fragmenr_signout) {
    private var _binding: FragmenrSignoutBinding? = null
    private val binding get() = _binding!!
    private lateinit var authViewModel: AuthViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= FragmenrSignoutBinding.bind(view)

        authViewModel.userData.observe(viewLifecycleOwner){
            binding.tv.text=it!!.email
        }

        binding.btnSignOut.setOnClickListener {
            authViewModel.signOut()
            authViewModel.loggedStatus.observe(viewLifecycleOwner){
                if (it == false){
                    findNavController().navigate(SignOutFragmentDirections.actionSignOutFragmentToDashboardFragment())
                }
            }

        }



    }//FUN






}