package com.handypawan.mvvmexampleproject.ui.home.quotes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.handypawan.mvvmexampleproject.R
import com.handypawan.mvvmexampleproject.ui.home.profile.ProfileViewModel
import com.handypawan.mvvmexampleproject.ui.home.profile.ProfileViewModelFactory
import com.handypawan.mvvmexampleproject.utils.Coroutines
import com.handypawan.mvvmexampleproject.utils.toast
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(),KodeinAware {

    override val kodein: Kodein by kodein()

    private lateinit var viewModel: QuotesViewModel
    private val factory: QuotesViewModelFactory by instance<QuotesViewModelFactory>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(QuotesViewModel::class.java)

        Coroutines.main{
            val quotes = viewModel.quotes.await()
            quotes.observe(viewLifecycleOwner, Observer {
                context?.toast(it.size.toString())
            })
        }
    }
}