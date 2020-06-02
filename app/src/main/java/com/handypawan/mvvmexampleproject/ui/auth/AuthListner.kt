package com.handypawan.mvvmexampleproject.ui.auth

import androidx.lifecycle.LiveData

/**
 * Created by pawan on 03,June,2020
 */
interface AuthListner {

    fun onStarted()
    fun onSuccess(loginResponse: LiveData<String>)
    fun onFailed(message: String)
}