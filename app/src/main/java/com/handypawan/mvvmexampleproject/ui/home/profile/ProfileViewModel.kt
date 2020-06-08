package com.handypawan.mvvmexampleproject.ui.home.profile

import androidx.lifecycle.ViewModel
import com.handypawan.mvvmexampleproject.data.repositories.UserRepository

class ProfileViewModel(repository: UserRepository) : ViewModel() {
    val user = repository.getUser()
}