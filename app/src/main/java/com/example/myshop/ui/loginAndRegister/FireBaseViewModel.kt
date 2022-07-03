package com.example.myshop.ui.loginAndRegister

import android.net.Uri
import androidx.lifecycle.*
import com.example.myshop.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.launch

class FireBaseViewModel() : ViewModel() {
    private val repository = FireBaseRepository()

    private val _userInfoLiveData=MutableLiveData<User>()
    val userInfoLiveData:LiveData<User> =_userInfoLiveData

    private val _uploadedImageUrl=MutableLiveData<Task<Uri>>()
    val uploadedImageUrl:LiveData<Task<Uri>> =_uploadedImageUrl



    fun saveUserToDatabase(userInfo:User){
        repository.saveUserToDatabase(userInfo)
    }

     suspend fun getUser(){
         viewModelScope.launch {
             repository.getUserDetail(_userInfoLiveData)
         }
    }

    suspend fun updateUserDetail(userInfo: HashMap<String,Any>): Task<Void> {
        return repository.updateUserDetail(userInfo)
    }

//    fun uploadImageToCloud(uri: Uri?, fileExtension:String?) {
//        viewModelScope.launch {
//            repository.uploadImageToCloud(uri, fileExtension, _uploadedImageUrl)
//        }
//    }












}