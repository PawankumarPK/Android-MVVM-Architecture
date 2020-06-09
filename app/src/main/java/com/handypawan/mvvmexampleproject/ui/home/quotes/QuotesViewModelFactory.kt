package com.handypawan.mvvmexampleproject.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.handypawan.mvvmexampleproject.data.repositories.QuoteRepository
import com.handypawan.mvvmexampleproject.data.repositories.UserRepository

/**
 * Created by pawan on 04,June,2020
 */
@Suppress("UNCHECKED_CAST")
class QuotesViewModelFactory(private val repository: QuoteRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(repository) as T
    }
}