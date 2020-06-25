package com.example.mydailyreminder.di

import android.content.Context
import com.example.mydailyreminder.data.database.ReminderDatabase
import com.example.mydailyreminder.data.database.daos.ReminderDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun providesReminderDatabase(@ApplicationContext appContext: Context): ReminderDatabase {
        return ReminderDatabase.getInstance(appContext)
    }

    @Singleton
    @Provides
    fun providesReminderDao(db: ReminderDatabase): ReminderDao {
        return db.reminderDao()
    }
}