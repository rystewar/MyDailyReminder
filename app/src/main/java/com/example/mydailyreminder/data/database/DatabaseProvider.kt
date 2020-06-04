package com.example.mydailyreminder.data.database

import android.content.Context

object DatabaseProvider {
    fun getReminderDataBase(appContext: Context): ReminderDatabase {
        return ReminderDatabase.getInstance(appContext)
    }
}