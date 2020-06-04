package com.example.mydailyreminder.repositories

import com.example.mydailyreminder.data.database.ReminderDatabase
import com.example.mydailyreminder.data.dataclasses.Reminder
import com.example.mydailyreminder.data.toDatabaseReminder
import com.example.mydailyreminder.data.toReminder

class ReminderRepository private constructor(reminderDatabase: ReminderDatabase) {
    private val reminderDao = reminderDatabase.reminderDao()

    suspend fun getReminders(): List<Reminder> {
        return reminderDao.getReminders().map { it.toReminder() }
    }

    suspend fun insertOrUpdateReminder(reminder: Reminder) {
        reminderDao.insertOrUpdateReminder(reminder.toDatabaseReminder())
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: ReminderRepository? = null

        fun getInstance(reminderDatabase: ReminderDatabase) =
            instance ?: synchronized(this) {
                instance ?: ReminderRepository(reminderDatabase).also { instance = it }
            }
    }
}