package com.sadeeq.app.projectprototype.roomDatabase

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val movieDao: MovieDao) {

    suspend fun insertMovies(movies: List<MovieEntity>) {
        movieDao.insertAll(movies)
    }

    fun getAllMovies(): Flow<List<MovieEntity>> {
        return movieDao.getAllMovies()
    }
}


