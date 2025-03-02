package com.test.kmtest.view.firstscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _isPalindrome = MutableLiveData<Boolean>()
    val isPalindrome: LiveData<Boolean> get() = _isPalindrome

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    fun setName(input: String) {
        _name.value = input
    }

    fun checkPalindrome(input: String) {
        val cleanedInput = input.replace(" ", "").lowercase()
        val isPalindrome = cleanedInput == cleanedInput.reversed()
        _isPalindrome.value = isPalindrome
    }
}