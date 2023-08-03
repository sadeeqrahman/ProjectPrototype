package com.sadeeq.app.projectprototype.roomDatabase

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {
    fun getAllMovies(): Flow<List<MovieEntity>> {
        return repository.getAllMovies()
    }

    suspend fun insertMovies(movies: List<MovieEntity>) {
        repository.insertMovies(movies)
    }
}

