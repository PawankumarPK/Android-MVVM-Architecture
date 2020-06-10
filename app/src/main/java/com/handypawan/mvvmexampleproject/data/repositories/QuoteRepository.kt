package com.handypawan.mvvmexampleproject.data.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.handypawan.mvvmexampleproject.data.db.AppDatabase
import com.handypawan.mvvmexampleproject.data.db.entities.Quote
import com.handypawan.mvvmexampleproject.data.network.MyApi
import com.handypawan.mvvmexampleproject.data.network.SafeApiRequest
import com.handypawan.mvvmexampleproject.data.preferences.PreferenceProvider
import com.handypawan.mvvmexampleproject.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

/**
 * Created by pawan on 08,June,2020
 */

private val MINIMUM_INTERVAL = 6
class QuoteRepository(private val api: MyApi, private val db: AppDatabase, private val prefs: PreferenceProvider) : SafeApiRequest() {

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
        val lastSavedAt = prefs.getLastSavedAt()
        if (lastSavedAt== null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))) {
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ChronoUnit.HOURS.between(savedAt,LocalDateTime.now()) > MINIMUM_INTERVAL
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
            prefs.saveLastSavedIt(LocalDateTime.now().toString())
            db.getQuoteDao().saveAllQuotes(quotes)
        }

    }
}