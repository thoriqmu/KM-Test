package com.test.kmtest.view.firstscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.test.kmtest.databinding.ActivityMainBinding
import com.test.kmtest.view.secondscreen.SecondScreenActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.isPalindrome.observe(this) { isPalindrome ->
            val message = if (isPalindrome) "isPalindrome" else "not palindrome"
            AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        viewModel.name.observe(this) { name ->
            // Name untuk second screen
        }

        binding.btnCheck.setOnClickListener {
            val palindromeInput = binding.palindromeInput.text.toString()
            if (palindromeInput.isNotEmpty()) {
                viewModel.checkPalindrome(palindromeInput)
            } else {
                AlertDialog.Builder(this)
                    .setMessage("Please enter a sentence to check!")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        }

        binding.btnNext.setOnClickListener {
            val nameInput = binding.nameInput.text.toString()
            if (nameInput.isNotEmpty()) {
                viewModel.setName(nameInput)
                val intent = Intent(this, SecondScreenActivity::class.java)
                intent.putExtra("NAME", nameInput)
                startActivity(intent)
            } else {
                AlertDialog.Builder(this)
                    .setMessage("Please enter your name!")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        }
    }
}