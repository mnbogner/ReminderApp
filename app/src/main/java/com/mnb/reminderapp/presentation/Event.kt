package com.mnb.reminderapp.presentation

sealed class Event {
    class Init(): Event()
    class ToggleShowToday(): Event()
    data class ItemAdd(val item: ReminderItem) : Event()
    data class ItemProgress(val item: ReminderItem) : Event()
    data class ItemComplete(val item: ReminderItem) : Event()
    data class ItemDelay(val item: ReminderItem) : Event()
    data class ItemSkip(val item: ReminderItem) : Event()
}