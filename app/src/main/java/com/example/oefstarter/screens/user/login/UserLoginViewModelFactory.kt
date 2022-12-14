package com.example.oefstarter.screens.user.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserLoginViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserLoginViewModel::class.java)) {
            return UserLoginViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
