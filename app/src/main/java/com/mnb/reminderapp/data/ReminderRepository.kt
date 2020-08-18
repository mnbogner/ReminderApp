package com.mnb.reminderapp.data

import com.mnb.reminderapp.domain.Reminder

class ReminderRepository(private val dataSource: ReminderDataSource) {
    suspend fun addReminder(reminder: Reminder): List<Reminder> {
        return dataSource.add(reminder)
    }
    suspend fun removeReminder(reminder: Reminder): List<Reminder> {
        return dataSource.remove(reminder)
    }
    suspend fun getReminders(): List<Reminder> {
        return dataSource.get()
    }
}