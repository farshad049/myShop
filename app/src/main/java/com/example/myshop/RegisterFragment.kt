package com.example.myshop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myshop.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar

class RegisterFragment:BaseFragment(R.layout.fragment_register) {
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
            binding.etEditFirstName.text.toString().trim().isEmpty() -> {
                Snackbar.make(mainActivity.findViewById(android.R.id.content),"please enter name",Snackbar.LENGTH_LONG).show()
                false}
            binding.etEditLastName.text.toString().trim().isEmpty() -> {
                Snackbar.make(mainActivity.findViewById(android.R.id.content),"please enter last name",Snackbar.LENGTH_LONG).show()
                false}
            binding.etEditEmail.text.toString().trim().isEmpty() -> {
                Snackbar.make(mainActivity.findViewById(android.R.id.content),"please enter email",Snackbar.LENGTH_LONG).show()
                false}
            binding.etEditPassword.text.toString().trim().isEmpty() -> {
                Snackbar.make(mainActivity.findViewById(android.R.id.content),"please enter password",Snackbar.LENGTH_LONG).show()

                false}
            binding.etEditConfirm.text.toString().trim().isEmpty() -> {
                Snackbar.make(mainActivity.findViewById(android.R.id.content),"please confirm password",Snackbar.LENGTH_LONG).show()
                false}
            binding.etEditConfirm.text.toString().trim() != binding.etEditPassword.text.toString().trim() -> {
                Snackbar.make(mainActivity.findViewById(android.R.id.content),"password doesn't match",Snackbar.LENGTH_LONG).show()
                false}
            !binding.checkAgree.isChecked -> {
                Snackbar.make(mainActivity.findViewById(android.R.id.content),"please chech the agreement",Snackbar.LENGTH_LONG).show()
                false
            }

            else -> true
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}