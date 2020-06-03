package com.handypawan.mvvmexampleproject.data.repositories

import com.handypawan.mvvmexampleproject.data.network.MyApi
import com.handypawan.mvvmexampleproject.data.network.SafeApiRequest
import com.handypawan.mvvmexampleproject.data.network.responses.AuthResponse

/**
 * Created by pawan on 03,June,2020
 */
class UserRepository : SafeApiRequest() {

    /**Only from coroutine or another suspend fun*/
    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { MyApi().userLogin(email, password) }

    }
}