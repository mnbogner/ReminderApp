package com.mnb.reminderapp.usecase.interactors

import com.mnb.reminderapp.data.ReminderRepository
import com.mnb.reminderapp.domain.Reminder

class ReminderAdd (private val reminderRepository: ReminderRepository) {
    suspend operator fun invoke(reminder: Reminder): List<Reminder> {
        return reminderRepository.addReminder(reminder)
    }
}