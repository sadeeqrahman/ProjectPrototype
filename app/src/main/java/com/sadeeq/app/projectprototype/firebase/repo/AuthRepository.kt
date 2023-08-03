package com.sadeeq.app.projectprototype.firebase.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sadeeq.app.projectprototype.firebase.AuthError
import com.sadeeq.app.projectprototype.firebase.AuthState
import com.sadeeq.app.projectprototype.firebase.models.User
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

class AuthRepository @Inject constructor(private val firebaseAuth: FirebaseAuth) {

    private val _authStateLiveData: MutableLiveData<AuthState> = MutableLiveData()
    val authStateLiveData: LiveData<AuthState> = _authStateLiveData

    private val _authErrorLiveData: MutableLiveData<AuthError> = MutableLiveData()
    val authErrorLiveData: LiveData<AuthError> = _authErrorLiveData

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

    fun signInWithEmailAndPassword(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authStateLiveData.postValue(AuthState.Authenticated)
                } else {
                    _authStateLiveData.postValue(AuthState.NotAuthenticated)
                    val errorMessage = task.exception?.localizedMessage ?: "An error occurred"
                    val authError = AuthError.FirebaseError(errorMessage)
                    _authErrorLiveData.postValue(authError)
                }
            }.addOnFailureListener { exception ->
                _authStateLiveData.postValue(AuthState.NotAuthenticated)
                val errorMessage = exception.localizedMessage ?: "An error occurred"
                val authError = AuthError.FirebaseError(errorMessage)
                _authErrorLiveData.postValue(authError)
            }
    }

    fun signUpWithEmailAndPassword(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authStateLiveData.postValue(AuthState.Authenticated)
                } else {
                    _authStateLiveData.postValue(AuthState.NotAuthenticated)
                    val errorMessage = task.exception?.localizedMessage ?: "An error occurred"
                    val authError = AuthError.FirebaseError(errorMessage)
                    _authErrorLiveData.postValue(authError)
                }
            }.addOnFailureListener { exception ->
                _authStateLiveData.postValue(AuthState.NotAuthenticated)
                val errorMessage = exception.localizedMessage ?: "An error occurred"
                val authError = AuthError.FirebaseError(errorMessage)
                _authErrorLiveData.postValue(authError)
            }
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun signOut() {
        firebaseAuth.signOut()
        _authStateLiveData.postValue(AuthState.NotAuthenticated)
    }

    fun insertUserData(user: User, onSuccess: () -> Unit, onError: (AuthError) -> Unit) {
        val userRef = databaseReference.child("users")
        val newUserRef = userRef.push()
        newUserRef.setValue(user).addOnSuccessListener {
                onSuccess.invoke()
            }.addOnFailureListener { exception ->
                val errorMessage = exception.localizedMessage ?: "An error occurred"
                val authError = AuthError.DatabaseError(errorMessage)
                onError.invoke(authError)
            }
    }
}

