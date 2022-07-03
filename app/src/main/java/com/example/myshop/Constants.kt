package com.example.myshop

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.webkit.MimeTypeMap


object Constants {
    const val USERS:String="users"
    const val LOGGED_IN_USER:String="loggedInUser"

    // Firebase database field names
    const val FIRST_NAME: String = "firstName"
    const val LAST_NAME: String = "lastName"
    const val MOBILE: String = "mobile"
    const val GENDER: String = "gender"
    const val IMAGE : String="image"
    const val USER_PROFILE_IMAGE: String = "User_Profile_image"
    var uploadedImageUrl:String?=null



    lateinit var sharedPreferences: SharedPreferences
    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(
            "${BuildConfig.APPLICATION_ID}.shared_preferences", Context.MODE_PRIVATE)
    }

    //get the file extension
    fun getFileExtension(activity: Activity,uri: Uri?):String?{
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(activity.contentResolver.getType(uri!!))
    }


    fun getFileExtension1(uri: Uri?): String {
        val url = uri.toString()
        return url.substring(url.lastIndexOf(".") + 1)
    }













}