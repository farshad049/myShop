package com.example.myshop

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes layoutRes:Int) :Fragment(layoutRes) {
    protected val mainActivity:MainActivity
        get() = activity as MainActivity
}