package com.example.mydailyreminder.data.dataclasses

import java.io.Serializable

data class Reminder(
    var name: String,
    var description: String = "",
    var frequency: ReminderFrequency,
    var isEnabled: Boolean = true
) : Serializable