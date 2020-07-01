package com.example.mydailyreminder.repositories

import com.example.mydailyreminder.data.database.daos.ReminderDao
import com.example.mydailyreminder.data.dataclasses.Reminder
import com.example.mydailyreminder.data.toDatabaseReminder
import com.example.mydailyreminder.data.toReminder
import javax.inject.Inject

class ReminderRepository @Inject constructor(
    private val reminderDao: ReminderDao
) {
    suspend fun getReminders(): List<Reminder> {
        return reminderDao.getReminders().map { it.toReminder() }
    }

    suspend fun insertOrUpdateReminder(reminder: Reminder) {
        reminderDao.insertOrUpdateReminder(reminder.toDatabaseReminder())
    }

    suspend fun reminderExists(reminder: Reminder): Boolean {
        return reminderDao.reminderExists(reminder.name).isNotEmpty()
    }
}