package com.mnb.reminderapp.usecase.interactors

import com.mnb.reminderapp.data.ReminderRepository
import com.mnb.reminderapp.domain.Reminder

class ReminderSkip (private val reminderRepository: ReminderRepository) {
    suspend operator fun invoke(reminder: Reminder): List<Reminder> {
        // TODO: add logic to queue up another reminder for recurring reminders
        return reminderRepository.removeReminder(reminder)
    }
}