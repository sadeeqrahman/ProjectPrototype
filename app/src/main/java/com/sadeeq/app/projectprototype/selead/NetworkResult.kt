package com.sadeeq.app.projectprototype.selead

import com.sadeeq.app.projectprototype.enum.NetworkStatusCode

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error(val exception: Exception, val statusCode: NetworkStatusCode) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
}