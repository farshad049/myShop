package com.example.myshop

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import com.google.firebase.auth.FirebaseAuth

abstract class BaseFragment(@LayoutRes layoutRes:Int) :Fragment(layoutRes) {
    private lateinit var dialog: Dialog

    protected val mainActivity:MainActivity
        get() = activity as MainActivity

    val auth: FirebaseAuth = FirebaseAuth.getInstance()




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