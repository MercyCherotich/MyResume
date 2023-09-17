package com.example.myresume.viewmodel

import androidx.annotation.WorkerThread
import com.example.myresume.data.User
import com.example.myresume.data.UserDao
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val user: Flow<User> = userDao.getUser()
    val users: Flow<List<User>> = userDao.getAll()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insertUser(user)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(user: User) {
        userDao.updateUser(user)
    }
}