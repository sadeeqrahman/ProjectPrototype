package com.sadeeq.app.projectprototype.firebase.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sadeeq.app.projectprototype.R
import com.sadeeq.app.projectprototype.base.BaseActivity
import com.sadeeq.app.projectprototype.databinding.ActivityUsersBinding
import com.sadeeq.app.projectprototype.firebase.AuthError
import com.sadeeq.app.projectprototype.firebase.models.User
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class UsersActivity : BaseActivity() {
    private lateinit var binding: ActivityUsersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_users)


        binding.saveUser.setOnClickListener {
            fireBaseViewModel.insertUserData(
                User("123", binding.userName.text.toString(), binding.emailAddress.text.toString()),
                onSuccess = {
                    showTost("Data inserted successfully")
                },
                onError = { authError ->
                    when (authError) {
                        is AuthError.DatabaseError -> {
                            val errorMessage = authError.errorMessage
                            showTost("Database Error: $errorMessage")
                        }
                        else -> {

                        }
                    }
                }
            )
        }


        fireBaseViewModel.usersLiveData.observe(this) {
            binding.saveUser.text = ""
            for (name in it){
                binding.saveUser.append(name.username+"\n")
            }
        }

        fireBaseViewModel.fetchUsers()
    }
}