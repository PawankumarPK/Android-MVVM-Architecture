package com.handypawan.mvvmexampleproject.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel

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
        authListner?.onSuccess()
    }
}