package com.example.mydailyreminder.viewmodels

import androidx.lifecycle.ViewModel
import com.example.mydailyreminder.data.database.ReminderDatabase
import com.example.mydailyreminder.data.dataclasses.Reminder
import com.example.mydailyreminder.repositories.ReminderRepository

class CreateReminderViewModel constructor(reminderDatabase: ReminderDatabase) : ViewModel() {
    private val reminderRepository = ReminderRepository.getInstance(reminderDatabase)

    suspend fun createReminder(reminder: Reminder) {
        reminderRepository.insertOrUpdateReminder(reminder)
    }
}