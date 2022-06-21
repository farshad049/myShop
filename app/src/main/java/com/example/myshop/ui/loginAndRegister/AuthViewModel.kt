package com.example.myshop.ui.loginAndRegister

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AuthRepository = AuthRepository(application)
    val userData: MutableLiveData<FirebaseUser?> = repository.firebaseUserMutableLiveData
    val loggedStatus: MutableLiveData<Boolean> = repository.userLoggedMutableLiveData
    val resetPassword: MutableLiveData<Boolean> = repository.resetPasswordLiveData

    fun register(email: String?, pass: String?) {
        viewModelScope.launch {
            repository.register(email, pass)
        }
    }

    fun forgotPassword(email: String?){
        viewModelScope.launch{
            repository.forgotPassword(email)
        }
    }

     fun signIn(email: String?, pass: String?) {
         viewModelScope.launch {
             repository.signIn(email, pass)
         }
    }

    fun signOut() {
        repository.signOut()
    }

}