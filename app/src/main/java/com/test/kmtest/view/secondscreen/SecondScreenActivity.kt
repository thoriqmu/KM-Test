package com.test.kmtest.view.secondscreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.test.kmtest.databinding.ActivitySecondScreenBinding
import com.test.kmtest.view.thirdscreen.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding
    private lateinit var viewModel: SecondScreenViewModel

    private val userSelectionLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedUser = result.data?.getStringExtra("SELECTED_USER") ?: "Unknown User"
            viewModel.setSelectedUser(selectedUser)
        }
    }

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

        if (viewModel.selectedUser.value == null) {
            viewModel.setSelectedUser("Selected User Name") // Nilai default
        }

        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java)
            userSelectionLauncher.launch(intent)
        }
    }
}