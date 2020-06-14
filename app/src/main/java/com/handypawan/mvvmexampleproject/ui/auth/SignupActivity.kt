package com.handypawan.mvvmexampleproject.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.handypawan.mvvmexampleproject.R
import com.handypawan.mvvmexampleproject.data.db.entities.User
import com.handypawan.mvvmexampleproject.databinding.ActivityLoginBinding
import com.handypawan.mvvmexampleproject.databinding.ActivitySignUpBinding
import com.handypawan.mvvmexampleproject.ui.home.HomeActivity
import com.handypawan.mvvmexampleproject.utils.hide
import com.handypawan.mvvmexampleproject.utils.show
import com.handypawan.mvvmexampleproject.utils.snackbar
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.progress_bar
import okhttp3.internal.Internal.instance
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(),AuthListner, KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance<AuthViewModelFactory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySignUpBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
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