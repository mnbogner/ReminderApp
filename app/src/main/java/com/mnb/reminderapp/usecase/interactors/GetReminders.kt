package com.mnb.reminderapp.usecase.interactors

import com.mnb.reminderapp.data.ReminderRepository
import com.mnb.reminderapp.domain.Reminder

class GetReminders(private val reminderRepository: ReminderRepository) {
    suspend operator fun invoke(): List<Reminder> {
        return reminderRepository.getReminders()
    }
}