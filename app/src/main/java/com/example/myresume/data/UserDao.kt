package com.example.myresume.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE uid=1")
    fun getUser(): User

    @Query("SELECT * FROM user WHERE full_name LIKE :full_name LIMIT 1")
    fun findByName(full_name: String): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser( user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun delete(user: User)
}