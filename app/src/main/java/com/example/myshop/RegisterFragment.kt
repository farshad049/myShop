package com.example.myshop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myshop.databinding.FragmentRegisterBinding

class RegisterFragment:Fragment(R.layout.fragment_register) {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegisterBinding.bind(view)



        binding.BtnRegister.setOnClickListener {
            validateFields()
        }





    }//FUN

    //validate fields
    private fun validateFields():Boolean{
        return when{
            binding.etEditFirstName.toString().trim().isEmpty() -> {
                binding.etFirstName.error="* Required Field"
                false}
            binding.etEditLastName.toString().trim().isEmpty() -> {
                binding.etEditLastName.error="* Required Field"
                false}
            binding.etEditEmail.toString().trim().isEmpty() -> {
                binding.etEditEmail.error="* Required Field"
                false}
            binding.etEditPassword.toString().trim().isEmpty() -> {
                binding.etEditPassword.error="* Required Field"
                false}
            binding.etEditConfirm.toString().trim().isEmpty() -> {
                binding.etEditConfirm.error="* Required Field"
                false}
            binding.etEditConfirm.toString().trim() != binding.etEditPassword.text.toString().trim() -> {
                binding.etEditConfirm.error="* Password Doesn't match"
                false}

            else -> true
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}