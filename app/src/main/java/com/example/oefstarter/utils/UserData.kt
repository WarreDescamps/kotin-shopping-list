package com.example.oefstarter.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.oefstarter.models.User

class UserData {
    companion object {
        private val users: MutableLiveData<MutableList<User>> = seed()

        private fun seed(): MutableLiveData<MutableList<User>> {
            val userList = mutableListOf<User>()
            userList.add(User(1, "test@gmail.com", "Test123$"))
            userList.add(User(2, "test2@gmail.com", "Test123$"))
            val list = MutableLiveData<MutableList<User>>()
            list.value = userList
            return list
        }

        fun getUser(email: String, password: String): User? {
            return users.value?.firstOrNull { user -> user.email == email && user.password == password }
        }

        fun getUsers(): LiveData<Map<Long, String>> {
            val safeUser = mutableMapOf<Long, String>()
            for (user in users.value ?: mutableListOf()) {
                safeUser.put(user.id, user.email)
            }
            val list = MutableLiveData<Map<Long, String>>()
            list.value = safeUser
            return list
        }

        fun addShoppingListItem(user: User) {
            val oldValue = users.value ?: mutableListOf()
            oldValue.add(user)
            users.value = oldValue
        }

        fun removeShoppingListItem(user: User) {
            val oldValue = users.value ?: mutableListOf()
            oldValue.remove(user)
            users.value = oldValue
        }
    }
}