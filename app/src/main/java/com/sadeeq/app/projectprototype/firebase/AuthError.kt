package com.sadeeq.app.projectprototype.firebase


sealed class AuthError {
    object NoInternet : AuthError()
    data class FirebaseError(val errorMessage: String) : AuthError()
}