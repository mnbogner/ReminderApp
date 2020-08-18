package com.mnb.reminderapp.framework

import android.content.Context
import com.mnb.reminderapp.data.ReminderDataSource
import com.mnb.reminderapp.domain.Reminder
import com.mnb.reminderapp.framework.database.ReminderDatabase
import com.mnb.reminderapp.framework.database.ReminderEntity

class ReminderRoomDataSource (context: Context) : ReminderDataSource {

    private val reminderDao = ReminderDatabase.getInstance(context).reminderDao()

    override suspend fun add(reminder: Reminder): List<Reminder> {
        reminderDao.insertReminder(ReminderEntity(reminder.title, reminder.text, reminder.date, reminder.repeat, reminder.offset))
        return convertList(reminderDao.getReminders())
    }

    override suspend fun remove(reminder: Reminder): List<Reminder> {
        reminderDao.deleteReminder(ReminderEntity(reminder.title, reminder.text, reminder.date, reminder.repeat, reminder.offset))
        return convertList(reminderDao.getReminders())
    }

    override suspend fun get(): List<Reminder> {
        return convertList(reminderDao.getReminders())
    }

    private fun convertList(remindersIn: List<ReminderEntity>): List<Reminder> {
        var remindersOut = ArrayList<Reminder>()
        remindersIn.forEach {
            remindersOut.add(Reminder(it.title, it.text, it.date, it.repeat, it.offset))
        }
        return remindersOut
    }
}