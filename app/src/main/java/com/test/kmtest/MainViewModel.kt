package com.test.kmtest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _palindrome = MutableLiveData<String>()
    val palindrome: MutableLiveData<String>
        get() = _palindrome

    fun checkPalindrome(input: String) {
        _palindrome.value = if (input == input.reversed()) "Palindrome" else "Not Palindrome"
    }
}