package com.handypawan.mvvmexampleproject.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.handypawan.mvvmexampleproject.data.repositories.UserRepository
import com.handypawan.mvvmexampleproject.utils.ApiExceptions
import com.handypawan.mvvmexampleproject.utils.Coroutines

/**
 * Created by pawan on 02,June,2020
 */
class AuthViewModel(private val repository : UserRepository) : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListner: AuthListner? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View) {
        authListner?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListner?.onFailed("Invalid email or password")
            return
        }

        /**this is a bad practise because we cannot get instance of another class in a class,
        So we need to dependency injection*/

        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                //check User is not null
                authResponse.user?.let {
                    authListner?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListner?.onFailed(authResponse.message!!)
            } catch (e: ApiExceptions) {
                authListner?.onFailed(e.message!!)
            }
        }
    }
}