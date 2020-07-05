package com.example.mydailyreminder.data.database.daos

import androidx.room.*
import com.example.mydailyreminder.data.database.entities.DatabaseReminder

@Dao
interface ReminderDao {
    @Query("SELECT * FROM DatabaseReminder")
    suspend fun getReminders(): List<DatabaseReminder>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateReminder(reminder: DatabaseReminder)

    @Query("SELECT * FROM DatabaseReminder WHERE name IS :reminderName")
    suspend fun reminderExists(reminderName: String): List<DatabaseReminder>

    @Delete
    suspend fun deleteReminder(reminder: DatabaseReminder)
}