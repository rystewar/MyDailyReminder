package com.example.mydailyreminder.data

import com.example.mydailyreminder.data.database.entities.DatabaseReminder
import com.example.mydailyreminder.data.dataclasses.Reminder
import com.example.mydailyreminder.data.dataclasses.ReminderFrequency
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

fun ReminderFrequency.toJson(): String {
    return Json(JsonConfiguration.Stable).stringify(ReminderFrequency.serializer(), this)
}

//TODO: Look into TypeConverters
fun DatabaseReminder.toReminder(): Reminder {
    return Reminder(
        name,
        description,
        Json(JsonConfiguration.Stable).parse(ReminderFrequency.serializer(), frequency)
    )
}

//TODO: Look into TypeConverters
fun Reminder.toDatabaseReminder(): DatabaseReminder {
    return DatabaseReminder(name, description, frequency.toJson())
}