package com.handypawan.mvvmexampleproject.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.handypawan.mvvmexampleproject.data.repositories.UserRepository

/**
 * Created by pawan on 02,June,2020
 */
class AuthViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListner: AuthListner? = null

    fun onLoginButtonClick(view: View) {
        authListner?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListner?.onFailed("Invalid email or password")
            return
        }

        /**this is a bad practise because we cannot get instance of another class in a class,
         So we need to dependency injection*/

        val loginResponse = UserRepository().userLogin(email!!, password!!)
        authListner?.onSuccess(loginResponse)
    }
}