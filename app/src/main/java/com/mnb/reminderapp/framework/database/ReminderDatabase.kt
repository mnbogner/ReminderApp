package com.mnb.reminderapp.framework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ReminderEntity::class], version = 1)
abstract class ReminderDatabase : RoomDatabase() {

    abstract fun reminderDao(): ReminderDao

    companion object {

        val REMINDER_DATABASE_NAME = "reminder_database"

        @Volatile
        private var reminderInstance: ReminderDatabase? = null

        fun getInstance(context: Context): ReminderDatabase {
            val instance = reminderInstance
            if (instance != null) {
                return instance
            }

            synchronized(ReminderDatabase::class) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    ReminderDatabase::class.java,
                    REMINDER_DATABASE_NAME
                ).build()
                reminderInstance = newInstance
                return newInstance
            }
        }
    }
}