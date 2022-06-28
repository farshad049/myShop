package com.example.myshop.ui.loginAndRegister

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.myshop.BaseFragment
import com.example.myshop.R
import com.example.myshop.databinding.FragmenrSignoutBinding


class SignOutFragment:BaseFragment(R.layout.fragmenr_signout) {
    private var _binding: FragmenrSignoutBinding? = null
    private val binding get() = _binding!!
    private lateinit var authViewModel: FireBaseViewModel


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= FragmenrSignoutBinding.bind(view)




       binding.btnSignOut.setOnClickListener {
            auth.signOut()
            findNavController().navigateUp()

//

//                authViewModel.signOut()
//                authViewModel.loggedStatus.observe(viewLifecycleOwner){
//                    if (it == true){
//                        findNavController().navigateUp()
//                    }
//                }



        }







    }//FUN







    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}