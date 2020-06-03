package com.handypawan.mvvmexampleproject.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.handypawan.mvvmexampleproject.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by pawan on 04,June,2020
 */
class NetworkConnectionIntercepter(context: Context) : Interceptor{

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {

        if(!isInternetAvailable()){
            throw NoInternetException("Make sure you have active data connection")
        }
        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable() : Boolean{
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }

    }
}