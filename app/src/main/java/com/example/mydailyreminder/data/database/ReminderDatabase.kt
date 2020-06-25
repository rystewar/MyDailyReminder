package com.example.mydailyreminder.data.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mydailyreminder.data.database.daos.ReminderDao
import com.example.mydailyreminder.data.database.entities.DatabaseReminder

private const val DB_NAME = "reminder-db"

@Database(entities = [DatabaseReminder::class], version = 1)
abstract class ReminderDatabase : RoomDatabase() {
    abstract fun reminderDao(): ReminderDao

    // Can probably use Dagger/Hilt to make this singleton
    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: ReminderDatabase? = null

        fun getInstance(context: Context): ReminderDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ReminderDatabase {
            Log.d("@@@", "DB Created")
            return Room.databaseBuilder(context, ReminderDatabase::class.java, DB_NAME)
                .build()
        }
    }
}