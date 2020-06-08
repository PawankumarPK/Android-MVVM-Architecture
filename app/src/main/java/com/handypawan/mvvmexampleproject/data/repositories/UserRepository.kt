package com.handypawan.mvvmexampleproject.data.repositories

import com.handypawan.mvvmexampleproject.data.db.AppDatabase
import com.handypawan.mvvmexampleproject.data.db.entities.User
import com.handypawan.mvvmexampleproject.data.network.MyApi
import com.handypawan.mvvmexampleproject.data.network.SafeApiRequest
import com.handypawan.mvvmexampleproject.data.network.responses.AuthResponse

/**
 * Created by pawan on 03,June,2020
 */
class UserRepository(private val api: MyApi, private val db: AppDatabase) : SafeApiRequest() {

    /**Only from coroutine or another suspend fun*/
    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }

    suspend fun userSignup(name: String, email: String, password: String): AuthResponse {
        return apiRequest { api.userSignup(name, email, password) }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()
}