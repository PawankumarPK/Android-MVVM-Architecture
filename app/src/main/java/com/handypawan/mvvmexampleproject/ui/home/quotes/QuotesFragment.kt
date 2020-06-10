package com.handypawan.mvvmexampleproject.ui.home.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.handypawan.mvvmexampleproject.R
import com.handypawan.mvvmexampleproject.data.db.entities.Quote
import com.handypawan.mvvmexampleproject.utils.Coroutines
import com.handypawan.mvvmexampleproject.utils.hide
import com.handypawan.mvvmexampleproject.utils.show
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.quotes_fragment.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by kodein()

    private lateinit var viewModel: QuotesViewModel
    private val factory: QuotesViewModelFactory by instance<QuotesViewModelFactory>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(QuotesViewModel::class.java)

        /* Coroutines.main{
             val quotes = viewModel.quotes.await()
             quotes.observe(viewLifecycleOwner, Observer {
                 context?.toast(it.size.toString())
             })
         }*/

        bindUI()
    }

    //Extension function
    private fun bindUI() = Coroutines.main {
        progress_bar.show()
        viewModel.quotes.await().observe(viewLifecycleOwner, Observer {
            progress_bar.hide()
            initRecyclerView(it.toQuoteItem())
        })
    }

    private fun initRecyclerView(quoteItem: List<QuoteItem>) {
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(quoteItem)
        }

        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }

    }

    private fun List<Quote>.toQuoteItem(): List<QuoteItem> {
        return this.map {
            QuoteItem(it)
        }
    }
}