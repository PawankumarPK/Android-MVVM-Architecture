package com.handypawan.mvvmexampleproject.ui.auth

import androidx.lifecycle.ViewModel
import com.handypawan.mvvmexampleproject.data.db.entities.User
import com.handypawan.mvvmexampleproject.data.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by pawan on 02,June,2020
 */
class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    fun getLoggedInUser() = repository.getUser()

    suspend fun userLogin(email: String, password: String) =
        withContext(Dispatchers.IO) { repository.userLogin(email, password) }

    suspend fun userSignup(name: String, email: String, password: String) =
        withContext(Dispatchers.IO) { repository.userSignup(name, email, password) }

    suspend fun saveLoggedInUser(user: User) = repository.saveUser(user)

}