package com.handypawan.mvvmexampleproject.data.repositories

import com.handypawan.mvvmexampleproject.data.network.MyApi
import com.handypawan.mvvmexampleproject.data.network.responses.AuthResponse
import retrofit2.Response

/**
 * Created by pawan on 03,June,2020
 */
class UserRepository {

    /**Only from coroutine or another suspend fun*/
    suspend fun userLogin(email: String, password: String): Response<AuthResponse> {
        return MyApi().userLogin(email, password)
    }

}