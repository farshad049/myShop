package com.example.myshop.ui.loginAndRegister

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthRepository(private val application: Application) {

    val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser?> = MutableLiveData()
    val userLoggedMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val resetPasswordLiveData:MutableLiveData<Boolean> = MutableLiveData()

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun register(email: String?, pass: String?) {
        auth.createUserWithEmailAndPassword(email!!, pass!!).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                firebaseUserMutableLiveData.postValue(auth.currentUser)
            } else {
                firebaseUserMutableLiveData.postValue(null)
                Toast.makeText(application, task.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    suspend fun signIn(email: String?, pass: String?) {
        auth.signInWithEmailAndPassword(email!!, pass!!).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                firebaseUserMutableLiveData.postValue(auth.currentUser)
            } else {
                Toast.makeText(application, task.exception?.message, Toast.LENGTH_SHORT).show()
                firebaseUserMutableLiveData.postValue(null)

            }
        }
    }

    suspend fun forgotPassword(email: String?){
        auth.sendPasswordResetEmail(email!!).addOnCompleteListener {
            if (it.isSuccessful){
                resetPasswordLiveData.postValue(true)
            }else{
                Toast.makeText(application,it.exception?.message,Toast.LENGTH_LONG).show()
                resetPasswordLiveData.postValue(false)
            }
        }
    }

    fun signOut() {
        auth.signOut()
        userLoggedMutableLiveData.postValue(false)
        Toast.makeText(application,"logged out",Toast.LENGTH_LONG).show()
    }

    init {
        if (auth.currentUser != null) {
            firebaseUserMutableLiveData.postValue(auth.currentUser)
        }
    }
}