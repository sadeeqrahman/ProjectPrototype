package com.sadeeq.app.projectprototype.network

import com.sadeeq.app.projectprototype.models.MoviesListResponse
import com.sadeeq.app.projectprototype.network.Apis.Companion.POPULAR_MOVIES
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApisServices {

    @GET(POPULAR_MOVIES)
    suspend fun getPopularMoviesList(@Query("page") page: Int): Response<MoviesListResponse>


}