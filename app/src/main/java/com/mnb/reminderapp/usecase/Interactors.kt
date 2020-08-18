package com.mnb.reminderapp.usecase

import com.mnb.reminderapp.usecase.interactors.*

data class Interactors(

    val getReminders: GetReminders,
    val reminderAdd: ReminderAdd,
    val reminderProgress: ReminderProgress,
    val reminderComplete: ReminderComplete,
    val reminderDelay: ReminderDelay,
    val reminderSkip: ReminderSkip,
    val getScore: GetScore,
    val scoreProgress: ScoreProgress,
    val scoreComplete: ScoreComplete,
    val scoreDelay: ScoreDelay,
    val scoreSkip: ScoreSkip

)