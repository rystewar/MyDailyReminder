package com.example.mydailyreminder.dataclasses

data class ReminderFrequency (
    var daily: Boolean = false,
    var weekly: Boolean = false,
    var monthly: Boolean = false
)