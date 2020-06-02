package com.handypawan.mvvmexampleproject.ui.auth

/**
 * Created by pawan on 03,June,2020
 */
interface AuthListner {

    fun onStarted()
    fun onSuccess()
    fun onFailed(message: String)
}