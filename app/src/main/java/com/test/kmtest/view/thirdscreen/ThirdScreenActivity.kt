package com.test.kmtest.view.thirdscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.test.kmtest.databinding.ActivityThirdScreenBinding
import com.test.kmtest.view.UserAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private lateinit var viewModel: ThirdScreenViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ThirdScreenViewModel::class.java)

        userAdapter = UserAdapter { user ->
            val fullName = "${user.first_name ?: "Firstname"} ${user.last_name ?: "Lastname"}"
            val intent = Intent()
            intent.putExtra("SELECTED_USER", fullName)
            setResult(RESULT_OK, intent)
            finish()
        }
        binding.rvUsers.adapter = userAdapter

        lifecycleScope.launch {
            viewModel.users.collectLatest { pagingData ->
                userAdapter.submitData(pagingData)
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            userAdapter.refresh()
            binding.swipeRefresh.isRefreshing = false
        }

        userAdapter.addLoadStateListener { loadState ->
            val isLoading = loadState.refresh is androidx.paging.LoadState.Loading || loadState.append is androidx.paging.LoadState.Loading
            binding.progressBar.visibility = if (isLoading) android.view.View.VISIBLE else android.view.View.GONE

            val isEmpty = loadState.refresh is androidx.paging.LoadState.NotLoading && userAdapter.itemCount == 0
            binding.tvEmptyState.visibility = if (isEmpty) android.view.View.VISIBLE else android.view.View.GONE
            binding.rvUsers.visibility = if (isEmpty) android.view.View.GONE else android.view.View.VISIBLE
        }
    }
}