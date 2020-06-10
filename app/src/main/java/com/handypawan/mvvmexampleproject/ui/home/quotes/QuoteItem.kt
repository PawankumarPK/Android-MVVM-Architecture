package com.handypawan.mvvmexampleproject.ui.home.quotes

import com.handypawan.mvvmexampleproject.R
import com.handypawan.mvvmexampleproject.data.db.entities.Quote
import com.handypawan.mvvmexampleproject.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

/**
 * Created by pawan on 10,June,2020
 */
class QuoteItem(private val quote: Quote) : BindableItem<ItemQuoteBinding>() {

    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
    }
}