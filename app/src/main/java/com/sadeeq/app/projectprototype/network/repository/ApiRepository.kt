package com.sadeeq.app.projectprototype.network.repository

import com.sadeeq.app.projectprototype.network.ApisServices
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiServices: ApisServices,
) {
    suspend fun getPopularMoviesList(page: Int) = apiServices.getPopularMoviesList(page)
}