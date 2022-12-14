package com.example.oefstarter.screens.user.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oefstarter.databinding.FragmentUserLoginBinding
import com.example.oefstarter.models.User
import com.example.oefstarter.utils.UserData

class UserLoginViewModel : ViewModel() {
    private var _user = MutableLiveData<User?>()
    val user: MutableLiveData<User?>
        get() = _user

    private var _errors = MutableLiveData<String>()
    val errors: MutableLiveData<String>
        get() = _errors

    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    init {
        email.value = ""
        password.value = ""
    }

    fun onLogin() {
        val user = UserData.getUser(email.value ?: "", password.value ?: "")
        if (user == null) {
            errors.value = "Email en Wachtwoord combinatie was onjuist."
        }
        this._user.value = user
    }
}