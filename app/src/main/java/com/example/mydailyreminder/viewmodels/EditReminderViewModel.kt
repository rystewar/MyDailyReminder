package com.example.mydailyreminder.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.mydailyreminder.data.dataclasses.Reminder
import com.example.mydailyreminder.repositories.ReminderRepository

class EditReminderViewModel @ViewModelInject constructor(
    private val reminderRepository: ReminderRepository
) : ViewModel() {
    suspend fun createOrUpdateReminder(reminder: Reminder) {
        reminderRepository.insertOrUpdateReminder(reminder)
    }

    suspend fun reminderExists(reminder: Reminder): Boolean {
        return reminderRepository.reminderExists(reminder)
    }

    suspend fun deleteReminder(reminder: Reminder) {
        reminderRepository.deleteReminder(reminder)
    }
}