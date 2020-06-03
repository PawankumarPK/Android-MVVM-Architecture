package com.handypawan.mvvmexampleproject.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.handypawan.mvvmexampleproject.R
import com.handypawan.mvvmexampleproject.data.db.AppDatabase
import com.handypawan.mvvmexampleproject.data.db.entities.User
import com.handypawan.mvvmexampleproject.data.network.MyApi
import com.handypawan.mvvmexampleproject.data.repositories.UserRepository
import com.handypawan.mvvmexampleproject.databinding.ActivityLoginBinding
import com.handypawan.mvvmexampleproject.ui.home.HomeActivity
import com.handypawan.mvvmexampleproject.utils.hide
import com.handypawan.mvvmexampleproject.utils.show
import com.handypawan.mvvmexampleproject.utils.snackbar
import com.handypawan.mvvmexampleproject.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api = MyApi()
        val db = AppDatabase(this)
        val respository = UserRepository(api, db)
        val factory = AuthViewModelFactory(respository)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListner = this

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null){
                Intent(this,HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

    }

    override fun onStarted() {
        // toast("Login Started")
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
       // root_layout.snackbar("${user.name} is logged In")
        progress_bar.hide()
    }

    override fun onFailed(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)

    }
}