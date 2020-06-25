package com.example.mydailyreminder.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.mydailyreminder.data.dataclasses.Reminder
import com.example.mydailyreminder.repositories.ReminderRepository

class CreateReminderViewModel @ViewModelInject constructor(
    private val reminderRepository: ReminderRepository
) : ViewModel() {
    suspend fun createReminder(reminder: Reminder) {
        reminderRepository.insertOrUpdateReminder(reminder)
    }
}