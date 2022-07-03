package com.example.myshop

import android.content.Context
import android.content.SharedPreferences

object Constants {
    const val USERS:String="users"
    const val LOGGED_IN_USER:String="loggedInUser"

    // Firebase database field names
    const val FIRST_NAME: String = "firstName"
    const val LAST_NAME: String = "lastName"
    const val MOBILE: String = "mobile"
    const val GENDER: String = "gender"
    const val IMAGE: String = "image"



    lateinit var sharedPreferences: SharedPreferences
    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(
            "${BuildConfig.APPLICATION_ID}.shared_preferences", Context.MODE_PRIVATE)
    }





}