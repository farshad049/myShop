package com.example.myshop.ui.loginAndRegister

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myshop.BaseFragment
import com.example.myshop.R
import com.example.myshop.databinding.FragmentForgetPasswordBinding


class ForgetPasswordFragment: BaseFragment(R.layout.fragment_forget_password) {
    private var _binding: FragmentForgetPasswordBinding? = null
    private val binding get() = _binding!!
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= FragmentForgetPasswordBinding.bind(view)

        binding.btnsubmit.setOnClickListener {
            val email = binding.etEditEmail.text.toString().trim()
            if (email.isEmpty()) {
                binding.etEmail.error = "* please enter a valid email address"
                binding.etEditEmail.addTextChangedListener { binding.etEmail.error = null }
            } else {
              //  showProgressBar()
                authViewModel.forgotPassword(email)
                authViewModel.resetPassword.observe(viewLifecycleOwner) {
                    //dismissProgressBar()
                    if (it == true) {
                        Toast.makeText(requireContext(),"reset email hase been sent to ${binding.etEditEmail.text}",Toast.LENGTH_LONG).show()
                        findNavController().navigate(ForgetPasswordFragmentDirections.actionForgetPasswordFragmentToLoginFragment())
                    }
                }
            }
        }





    }//FUN









    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}