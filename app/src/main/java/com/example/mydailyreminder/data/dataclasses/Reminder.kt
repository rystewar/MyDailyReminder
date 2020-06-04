package com.example.mydailyreminder.data.dataclasses

data class Reminder(
    var name: String,
    var description: String = "",
    var frequency: ReminderFrequency
)