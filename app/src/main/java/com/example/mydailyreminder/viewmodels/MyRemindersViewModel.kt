package com.example.mydailyreminder.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.mydailyreminder.data.dataclasses.Reminder
import com.example.mydailyreminder.repositories.ReminderRepository

class MyRemindersViewModel @ViewModelInject constructor(
    private val reminderRepository: ReminderRepository
) : ViewModel() {
    suspend fun getReminders(): List<Reminder> {
        return reminderRepository.getReminders()
    }

    suspend fun updateReminder(reminder: Reminder) {
        reminderRepository.insertOrUpdateReminder(reminder)
    }
}