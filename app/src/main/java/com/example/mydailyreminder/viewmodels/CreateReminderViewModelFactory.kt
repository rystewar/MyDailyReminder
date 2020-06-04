package com.example.mydailyreminder.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mydailyreminder.data.database.ReminderDatabase

// This viewmodel factory is needed in order to inject the database
class CreateReminderViewModelFactory(
    private val reminderDatabase: ReminderDatabase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateReminderViewModel(reminderDatabase) as T
    }
}