package com.sadeeq.app.projectprototype.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sadeeq.app.projectprototype.models.MoviesListResponse
import com.sadeeq.app.projectprototype.network.repository.ApiRepository
import retrofit2.HttpException

class MoviesPagingSource(
    private val repository: ApiRepository,
) : PagingSource<Int, MoviesListResponse.Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesListResponse.Result> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.getPopularMoviesList(currentPage)
            val data = response.body()!!.results
            val responseData = mutableListOf<MoviesListResponse.Result>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }


    override fun getRefreshKey(state: PagingState<Int, MoviesListResponse.Result>): Int? {
        return null
    }

}

