package com.handypawan.mvvmexampleproject.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.handypawan.mvvmexampleproject.R
import com.handypawan.mvvmexampleproject.databinding.ActivityLoginBinding
import com.handypawan.mvvmexampleproject.utils.hide
import com.handypawan.mvvmexampleproject.utils.show
import com.handypawan.mvvmexampleproject.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListner = this

    }

    override fun onStarted() {
        toast("Login Started")
        progress_bar.show()

    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(this, Observer {
            progress_bar.hide()
            toast(it)
        })


    }

    override fun onFailed(message: String) {
        progress_bar.hide()
        toast(message)

    }
}