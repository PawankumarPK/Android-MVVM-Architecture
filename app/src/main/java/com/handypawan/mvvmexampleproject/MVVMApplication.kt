package com.handypawan.mvvmexampleproject

import android.app.Application
import com.handypawan.mvvmexampleproject.data.db.AppDatabase
import com.handypawan.mvvmexampleproject.data.network.MyApi
import com.handypawan.mvvmexampleproject.data.network.NetworkConnectionIntercepter
import com.handypawan.mvvmexampleproject.data.repositories.QuoteRepository
import com.handypawan.mvvmexampleproject.data.repositories.UserRepository
import com.handypawan.mvvmexampleproject.ui.auth.AuthViewModelFactory
import com.handypawan.mvvmexampleproject.ui.home.profile.ProfileViewModelFactory
import com.handypawan.mvvmexampleproject.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Created by pawan on 04,June,2020
 */
class MVVMApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionIntercepter(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { QuoteRepository(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }

    }
}