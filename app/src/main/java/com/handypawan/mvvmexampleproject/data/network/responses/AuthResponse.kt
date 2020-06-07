package com.handypawan.mvvmexampleproject.data.network.responses

import com.handypawan.mvvmexampleproject.data.db.entities.User

/**
 * Created by pawan on 03,June,2020
 */

//To store JSON response
data class AuthResponse(
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?


)
