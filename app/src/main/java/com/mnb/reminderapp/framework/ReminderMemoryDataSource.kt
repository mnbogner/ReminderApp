package com.mnb.reminderapp.framework

import com.mnb.reminderapp.data.ReminderDataSource
import com.mnb.reminderapp.domain.Reminder
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ReminderMemoryDataSource : ReminderDataSource {

    private val reminderMap = HashMap<String, Reminder>()

    override suspend fun add(reminder: Reminder): List<Reminder> {
        reminderMap.put(reminder.title, reminder)
        var reminders = ArrayList<Reminder>()
        reminders.addAll(reminderMap.values)
        return reminders
    }

    override suspend fun remove(reminder: Reminder): List<Reminder> {
        reminderMap.remove(reminder.title)
        var reminders = ArrayList<Reminder>()
        reminders.addAll(reminderMap.values)
        return reminders
    }

    override suspend fun get(): List<Reminder> {
        var reminders = ArrayList<Reminder>()
        reminders.addAll(reminderMap.values)
        return reminders
    }
}