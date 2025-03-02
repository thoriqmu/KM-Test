package com.test.kmtest.view.secondscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SecondScreenViewModel : ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    private val _selectedUser = MutableLiveData<String>()
    val selectedUser: LiveData<String> get() = _selectedUser

    fun setName(name: String) {
        _name.value = name
    }

    fun setSelectedUser(user: String) {
        _selectedUser.value = user
    }
}