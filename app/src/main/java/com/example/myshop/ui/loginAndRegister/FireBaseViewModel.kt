package com.example.myshop.ui.loginAndRegister

import androidx.lifecycle.*
import com.example.myshop.model.User
import kotlinx.coroutines.launch

class FireBaseViewModel() : ViewModel() {
    private val repository = FireBaseRepository()

    private val _userInfoLiveData=MutableLiveData<User>()
    val userInfoLiveData:LiveData<User> =_userInfoLiveData



    fun saveUserToDatabase(userInfo:User){
        repository.saveUserToDatabase(userInfo)
    }

     suspend fun getUser(){
         viewModelScope.launch {
             repository.getUserDetail(_userInfoLiveData)
         }
    }







}