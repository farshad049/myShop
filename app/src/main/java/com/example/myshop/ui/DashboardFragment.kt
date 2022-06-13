package com.example.myshop.ui

import android.os.Bundle
import android.view.View
import com.example.myshop.BaseFragment
import com.example.myshop.R
import com.example.myshop.databinding.FragmentDashboardBinding


class DashboardFragment: BaseFragment(R.layout.fragment_dashboard) {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= FragmentDashboardBinding.bind(view)






    }//FUN




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}