package com.sadeeq.app.projectprototype.firebase

sealed class AuthState {
    object Authenticated : AuthState()
    object NotAuthenticated : AuthState()
}