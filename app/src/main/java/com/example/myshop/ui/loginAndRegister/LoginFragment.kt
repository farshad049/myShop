package com.example.myshop.ui.loginAndRegister

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myshop.BaseFragment
import com.example.myshop.Constants
import com.example.myshop.R
import com.example.myshop.databinding.FragmentLoginBinding
import com.example.myshop.model.User
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import kotlin.math.log


class LoginFragment: BaseFragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: FireBaseViewModel by viewModels()


    //private lateinit var authViewModel: AuthViewModel

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
//    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding=FragmentLoginBinding.bind(view)


        binding.tvRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        binding.tvForgetPassword.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgetPasswordFragment())
        }


        binding.btnLogin.setOnClickListener {
            val email=binding.etEditLoginEmail.text.toString().trim()
            val password=binding.etEditLoginPassword.text.toString().trim()

            when{
                email.isEmpty() -> {
                    Snackbar.make(mainActivity.findViewById(android.R.id.content),"please enter name", Snackbar.LENGTH_LONG).show()
                }
                password.isEmpty() ->{
                    Snackbar.make(mainActivity.findViewById(android.R.id.content),"please enter name", Snackbar.LENGTH_LONG).show()
                }
                else -> {


//                        showProgressBar()
//                        authViewModel.signIn(email,password)
//                        dismissProgressBar()
//                        authViewModel.isSuccess.observe(viewLifecycleOwner){
//                                if (it == true) {
//                                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignOutFragment())
//                                }else{
//                                    Toast.makeText(requireContext(),"ridi", Toast.LENGTH_LONG).show()
//                                }
//                            }

                    showProgressBar()
                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                        dismissProgressBar()
                        if (it.isSuccessful){
                               lifecycleScope.launch {

                                   authViewModel.getUser()
                                   authViewModel.userInfoLiveData.observe(viewLifecycleOwner){it2 ->
                                   Log.e("dashagh",it2.email) }
                                }
                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignOutFragment())
                        }else{
                            Toast.makeText(requireContext(),it.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                        }
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