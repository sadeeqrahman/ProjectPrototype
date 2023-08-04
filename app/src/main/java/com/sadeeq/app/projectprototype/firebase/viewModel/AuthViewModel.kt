package com.sadeeq.app.projectprototype.firebase.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.sadeeq.app.projectprototype.firebase.AuthError
import com.sadeeq.app.projectprototype.firebase.AuthState
import com.sadeeq.app.projectprototype.firebase.models.User
import com.sadeeq.app.projectprototype.firebase.repo.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    val authState: LiveData<AuthState> = authRepository.authStateLiveData
    val authError: LiveData<AuthError> = authRepository.authErrorLiveData

    private val _usersLiveData: MutableLiveData<List<User>> = MutableLiveData()
    val usersLiveData: LiveData<List<User>> = _usersLiveData

    fun signIn(email: String, password: String) {
        authRepository.signInWithEmailAndPassword(email, password)
    }

    fun signUp(email: String, password: String) {
        authRepository.signUpWithEmailAndPassword(email, password)
    }

    fun signOut() {
        authRepository.signOut()
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun insertUserData(user: User, onSuccess: () -> Unit, onError: (AuthError) -> Unit) {
        authRepository.insertUserData(user,
            onSuccess = {
                onSuccess.invoke()
            },
            onError = { databaseError ->
                onError.invoke(databaseError)
            }
        )
    }

    fun fetchUsers() {
        authRepository.getUsers(
            onSuccess = { users ->
                _usersLiveData.postValue(users)
            },
            onError = { errorMessage ->
                // Handle error
            }
        )
    }
}
