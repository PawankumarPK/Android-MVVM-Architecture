package com.handypawan.mvvmexampleproject.data.network

import com.google.gson.Gson
import com.handypawan.mvvmexampleproject.data.network.responses.AuthResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by pawan on 03,June,2020
 */
interface MyApi {

    @FormUrlEncoded
    @POST("login")
/**
    suspending fun is simply a fun that can be paused and resumed at a later time. So these
    type of function can excute a long running operation and wait for complete without blocking.
    As our fun userLogin perform a network operation and it is a long running operation*/
    suspend fun userLogin(@Field("email") email: String, @Field("password") password: String):
            Response<AuthResponse>

    companion object {
        operator fun invoke(): MyApi {
            return Retrofit.Builder()
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}