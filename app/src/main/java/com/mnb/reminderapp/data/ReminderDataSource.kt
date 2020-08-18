package com.mnb.reminderapp.data

import com.mnb.reminderapp.domain.Reminder

interface ReminderDataSource {
    suspend fun add(reminder: Reminder): List<Reminder>
    suspend fun remove(reminder: Reminder): List<Reminder>
    suspend fun get(): List<Reminder>
}