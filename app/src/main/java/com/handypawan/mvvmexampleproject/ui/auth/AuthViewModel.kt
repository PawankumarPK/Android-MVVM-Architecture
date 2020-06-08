package com.handypawan.mvvmexampleproject.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.handypawan.mvvmexampleproject.data.repositories.UserRepository
import com.handypawan.mvvmexampleproject.utils.ApiExceptions
import com.handypawan.mvvmexampleproject.utils.Coroutines
import com.handypawan.mvvmexampleproject.utils.NoInternetException

/**
 * Created by pawan on 02,June,2020
 */
class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var passwordConfirm: String? = null

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
            } catch (e: NoInternetException) {
                authListner?.onFailed(e.message!!)
            }
        }
    }

    fun onSignup(view: View){
        Intent(view.context,SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onLogin(view: View){
        Intent(view.context,LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onSignupButtonClick(view: View) {
        authListner?.onStarted()

        if(name.isNullOrEmpty()){
            authListner?.onFailed("Name is required")
            return
        }

        if (email.isNullOrEmpty()) {
            authListner?.onFailed("Email is required")
            return
        }

        if (password.isNullOrEmpty()) {
            authListner?.onFailed("Please Enter a password")
            return
        }
        if(password != passwordConfirm){
            authListner?.onFailed("Password did not match")
            return
        }

        Coroutines.main {
            try {
                val authResponse = repository.userSignup(name!!,email!!, password!!)
                //check User is not null
                authResponse.user?.let {
                    authListner?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListner?.onFailed(authResponse.message!!)
            } catch (e: ApiExceptions) {
                authListner?.onFailed(e.message!!)
            } catch (e: NoInternetException) {
                authListner?.onFailed(e.message!!)
            }
        }
    }
}