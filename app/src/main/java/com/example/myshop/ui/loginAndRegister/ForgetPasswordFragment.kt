package com.example.myshop.ui.loginAndRegister

import android.os.Bundle
import android.view.View
import com.example.myshop.BaseFragment
import com.example.myshop.R
import com.example.myshop.databinding.FragmentForgetPasswordBinding


class ForgetPasswordFragment: BaseFragment(R.layout.fragment_forget_password) {
    private var _binding: FragmentForgetPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= FragmentForgetPasswordBinding.bind(view)



    }//FUN









    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}