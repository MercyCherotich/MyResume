package com.example.myresume.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "full_name") val fullName: String?,
    @ColumnInfo(name = "slack_name") val slackUserName: String?,
    @ColumnInfo(name = "github_handle") val githubHandle: String?,
    @ColumnInfo(name = "user_bio") val userBio: String?
)
