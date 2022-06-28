package com.example.myshop.ui.loginAndRegister

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myshop.Constants
import com.example.myshop.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FireBaseRepository() {

    private val mFireStore = FirebaseFirestore.getInstance()





    fun saveUserToDatabase(userInfo:User) {
        // The "users" is collection name. If the collection is already created then it will not create the same one again.
        mFireStore.collection(Constants.USERS)
            // Document ID for users fields. Here the document it is the User ID.
            .document(userInfo.id)
            // Here the userInfo are Field and the SetOption is set to merge. It is for if we wants to merge later on instead of replacing the fields.
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {

            }
            .addOnFailureListener {
                Log.e("Error while registering the user.", it.toString())
            }
    }



    suspend fun getUserDetail(userInfo: MutableLiveData<User>){
        mFireStore.collection(Constants.USERS)
                //get the user uuid from authenticated user as document
            .document(FirebaseAuth.getInstance().currentUser!!.uid)
            .get()
            .addOnSuccessListener {user->
                val response=user.toObject(User::class.java)
                userInfo.postValue(response)

                Constants.sharedPreferences.edit().putString(Constants.LOGGED_IN_USER,"${response!!.firstName} ${response!!.lastName}").apply()
            }
            .addOnFailureListener {
                Log.e("Error while getting the user information.", it.toString())
            }
    }


}