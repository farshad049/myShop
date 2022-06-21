package com.example.myshop

import android.app.Dialog
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections

abstract class BaseFragment(@LayoutRes layoutRes:Int) :Fragment(layoutRes) {
    private lateinit var dialog: Dialog

    protected val mainActivity:MainActivity
        get() = activity as MainActivity



    fun showProgressBar(){
        dialog=Dialog(requireContext())
        dialog.setContentView(R.layout.model_loading)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    fun dismissProgressBar(){
        dialog.dismiss()
    }
}