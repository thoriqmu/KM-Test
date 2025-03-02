package com.test.kmtest.view.secondscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.test.kmtest.databinding.ActivitySecondScreenBinding

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding
    private lateinit var viewModel: SecondScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SecondScreenViewModel::class.java]

        val name = intent.getStringExtra("NAME") ?: "John Doe"
        viewModel.setName(name)

        viewModel.name.observe(this) { name ->
            binding.tvName.text = name
        }

        viewModel.selectedUser.observe(this) { selectedUser ->
            binding.tvSelectedUser.text = selectedUser
        }
    }
}