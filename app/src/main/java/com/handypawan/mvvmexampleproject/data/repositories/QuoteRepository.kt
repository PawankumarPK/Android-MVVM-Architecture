package com.handypawan.mvvmexampleproject.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.handypawan.mvvmexampleproject.data.db.AppDatabase
import com.handypawan.mvvmexampleproject.data.db.entities.Quote
import com.handypawan.mvvmexampleproject.data.network.MyApi
import com.handypawan.mvvmexampleproject.data.network.SafeApiRequest
import com.handypawan.mvvmexampleproject.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by pawan on 08,June,2020
 */
class QuoteRepository(private val api: MyApi, private val db: AppDatabase) : SafeApiRequest() {


    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quote>> {
        return withContext(Dispatchers.IO) {
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }

    private suspend fun fetchQuotes() {
        if (isFetchNeeded()) {
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeeded(): Boolean {
        return true
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
            db.getQuoteDao().saveAllQuotes(quotes)
        }

    }
}