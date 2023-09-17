@file:OptIn(InternalCoroutinesApi::class, InternalCoroutinesApi::class)

package com.example.myresume.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import kotlinx.coroutines.launch

@Database(entities = [User :: class], version = 2)
abstract class UserRoomDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    private class UserDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database: UserRoomDatabase ->
                scope.launch {
                    populateDatabase(database.userDao())
                }
            }
        }
        suspend fun populateDatabase(userDao: UserDao) {

            var user = User(1, "Mercy Cherotich", "Mercy Cherotich",
                "Cherotich", "I am computer scientist.")
            userDao.insertUser(user)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserRoomDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope
        ): UserRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder<UserRoomDatabase>(
                    context.applicationContext,
                    UserRoomDatabase::class.java,
                    "user_room_database"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}