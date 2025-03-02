package com.test.kmtest.view

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.test.kmtest.data.remote.response.DataItem
import com.test.kmtest.data.remote.retrofit.ApiConfig

class UserPagingSource : PagingSource<Int, DataItem>() {

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val page = params.key ?: 1
            val perPage = params.loadSize

            val response = ApiConfig.getApiService().getUsers(page, perPage)
            if (response.isSuccessful) {
                val userResponse = response.body()
                val users = userResponse?.data?.filterNotNull() ?: emptyList()
                val totalPages = userResponse?.totalPages ?: 1

                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (page >= totalPages) null else page + 1

                LoadResult.Page(
                    data = users,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(Exception("Failed to load users: ${response.code()}"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}