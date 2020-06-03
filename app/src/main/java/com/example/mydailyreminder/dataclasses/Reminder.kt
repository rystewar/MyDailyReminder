package com.example.mydailyreminder.dataclasses

data class Reminder (
    var name: String,
    var description: String = "",
    var frequency: ReminderFrequency
)