package com.test.kmtest.view.thirdscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.test.kmtest.data.remote.response.DataItem
import com.test.kmtest.view.UserPagingSource
import kotlinx.coroutines.flow.Flow

class ThirdScreenViewModel : ViewModel() {

    val users: Flow<PagingData<DataItem>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { UserPagingSource() }
    ).flow.cachedIn(viewModelScope)
}