package com.example.myshop

import android.content.Context
import android.content.SharedPreferences

object Constants {
    const val USERS:String="users"
    const val LOGGED_IN_USER:String="loggedInUser"



    lateinit var sharedPreferences: SharedPreferences
    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(
            "${BuildConfig.APPLICATION_ID}.shared_preferences", Context.MODE_PRIVATE)
    }



}