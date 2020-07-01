package com.example.mydailyreminder.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

// Intended to be a database compatible copy of a Reminder
@Entity
data class DatabaseReminder (
    @PrimaryKey
    val name: String,
    val description: String = "",
    val frequency: String,
    val isEnabled: Boolean = true
)
