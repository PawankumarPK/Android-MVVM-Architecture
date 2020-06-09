package com.handypawan.mvvmexampleproject.data.network.responses

import com.handypawan.mvvmexampleproject.data.db.entities.Quote

/**
 * Created by pawan on 08,June,2020
 */
data class QuotesResponse(
    val isSuccessful : Boolean,
    val quotes : List<Quote>

)

