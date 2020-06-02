package com.handypawan.mvvmexampleproject.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.handypawan.mvvmexampleproject.data.network.MyApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by pawan on 03,June,2020
 */
class UserRepository {

    fun userLogin(email: String, password: String): LiveData<String> {

        val loginResponse = MutableLiveData<String>()

        MyApi().userLogin(email, password).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                loginResponse.value = t.message
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful)
                    loginResponse.value = response.body().toString()
                else
                    loginResponse.value = response.errorBody().toString()
            }
        })
        return loginResponse
    }
}