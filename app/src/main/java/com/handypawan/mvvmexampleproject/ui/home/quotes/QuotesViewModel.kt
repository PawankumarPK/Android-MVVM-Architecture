package com.handypawan.mvvmexampleproject.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.handypawan.mvvmexampleproject.data.repositories.QuoteRepository
import com.handypawan.mvvmexampleproject.utils.lazyDeferred

class QuotesViewModel(repository: QuoteRepository) : ViewModel() {

    /**We used when we dont need call function continuously we call when we need*/
    val quotes by lazyDeferred {
        repository.getQuotes()
    }
}