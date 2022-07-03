package com.example.myshop.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import coil.load
import com.example.myshop.BaseFragment
import com.example.myshop.Constants
import com.example.myshop.R
import com.example.myshop.databinding.FragmentDashboardBinding
import com.example.myshop.ui.loginAndRegister.FireBaseViewModel


class DashboardFragment: BaseFragment(R.layout.fragment_dashboard) {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FireBaseViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= FragmentDashboardBinding.bind(view)

        //take logged user from sharedPref and set the default to guest
        //val loggedUser=Constants.sharedPreferences.getString(Constants.LOGGED_IN_USER,"guest")

        val loggedUser=Constants.sharedPreferences.getString(Constants.LOGGED_IN_USER,"guest")

        binding.tv.text="Hello $loggedUser"








    }//FUN




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}