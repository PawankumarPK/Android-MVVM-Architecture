package com.handypawan.mvvmexampleproject.ui.auth

import androidx.lifecycle.LiveData
import com.handypawan.mvvmexampleproject.data.db.entities.User

/**
 * Created by pawan on 03,June,2020
 */
interface AuthListner {

    fun onStarted()
    fun onSuccess(user: User)
    fun onFailed(message: String)
}