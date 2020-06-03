package com.example.mydailyreminder.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydailyreminder.dataclasses.Reminder

class CreateReminderViewModel: ViewModel() {

    fun createReminder(reminder: Reminder) {
        Log.d("@@@", "Creating reminder: $reminder")
    }
}