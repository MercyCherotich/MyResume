package com.example.myresume.viewmodel

import androidx.lifecycle.*
import com.example.myresume.data.User
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    val user: User = repository.user

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }
    fun update(user: User) = viewModelScope.launch {
        repository.update(user)
    }
}

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}