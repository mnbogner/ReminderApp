package com.mnb.reminderapp.usecase.interactors

import com.mnb.reminderapp.data.ReminderRepository
import com.mnb.reminderapp.domain.Reminder
import java.util.*

// does it make sense to bake this logic in here? doing so does
// isolate it from both the presentation and framework layers

class ReminderProgress (private val reminderRepository: ReminderRepository) {
    suspend operator fun invoke(reminder: Reminder): List<Reminder> {
        var calendar = Calendar.getInstance()
        calendar.time = Date(reminder.date)
        // increment the day and increment the offset to reflect this
        var year = calendar.get(Calendar.YEAR)
        var day = calendar.get(Calendar.DAY_OF_YEAR)
        day = day + 1
        if (year % 4 == 0 && day > 366) {
            day = day - 366
            year = year + 1
        } else if (year % 4 > 0 && day > 365) {
            day = day - 365
            year = year + 1
        }
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.DAY_OF_YEAR, day)
        return reminderRepository.addReminder(Reminder(reminder.title, reminder.text, calendar.timeInMillis, reminder.repeat, reminder.offset + 1))
    }
}