package com.sadeeq.app.projectprototype.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.sadeeq.app.projectprototype.network.repository.ApiRepository
import com.sadeeq.app.projectprototype.paging.MoviesPagingSource
import com.sadeeq.app.projectprototype.selead.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ApiVIewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    val moviesList = Pager(PagingConfig(1)) {
        MoviesPagingSource(repository)
    }.flow.cachedIn(viewModelScope)


}