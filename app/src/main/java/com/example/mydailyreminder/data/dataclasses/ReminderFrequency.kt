package com.example.mydailyreminder.data.dataclasses

import kotlinx.serialization.Serializable

@Serializable
data class ReminderFrequency(
    var daily: Boolean = false,
    var weekly: Boolean = false,
    var monthly: Boolean = false
)