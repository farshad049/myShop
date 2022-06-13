package com.example.myshop.ui.loginAndRegister

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser

class AuthViewModel():ViewModel() {
    private val repository= AuthRepository()

    var userData= MutableLiveData<FirebaseUser?>()
    var loggedStatus= MutableLiveData<Boolean>()

    fun register(email: String, pass: String) {
        repository.register(email, pass)
    }

    fun signIn(email: String, pass: String) {
        repository.login(email, pass)
    }

    fun signOut() {
        repository.signOut()
    }

    init{
        //automatically set this variables to repository livedata variables ,to check if this data is not null
        userData = repository.firebaseUserMutableLiveData
        loggedStatus = repository.userLoggedMutableLiveData
    }
}