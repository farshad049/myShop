package com.example.myshop.ui.loginAndRegister

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myshop.BaseFragment
import com.example.myshop.R
import com.example.myshop.databinding.FragmentLoginBinding

import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment: BaseFragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    //private lateinit var auth: FirebaseAuth
    private val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding=FragmentLoginBinding.bind(view)

       // auth = Firebase.auth

        binding.tvRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        binding.btnLogin.setOnClickListener {
            val email=binding.etEditEmail.text.toString().trim()
            val password=binding.etEditPassword.text.toString().trim()

            when{
                email.isEmpty() -> {
                    Snackbar.make(mainActivity.findViewById(android.R.id.content),"please enter name", Snackbar.LENGTH_LONG).show()
                }
                password.isEmpty() ->{
                    Snackbar.make(mainActivity.findViewById(android.R.id.content),"please enter name", Snackbar.LENGTH_LONG).show()
                }
                else -> {
                    viewModel.signIn(email,password)
                    viewModel.userData.observe(viewLifecycleOwner) {
                        if (it != null) {
                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgetPasswordFragment())
                        } else {
                            Toast.makeText(requireContext(), "login failed", Toast.LENGTH_LONG).show()
                        }
                    }

//                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
//                        if (it.isSuccessful){
//                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgetPasswordFragment())
//                        }else{
//                            Toast.makeText(requireContext(),"login failed", Toast.LENGTH_LONG).show()
//                        }
//                    }
                }
            }
        }






    }//FUN






    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}