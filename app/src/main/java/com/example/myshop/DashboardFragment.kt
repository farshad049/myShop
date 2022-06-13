package com.example.myshop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myshop.databinding.FragmentDashboardBinding


class DashboardFragment:BaseFragment(R.layout.fragment_dashboard) {
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