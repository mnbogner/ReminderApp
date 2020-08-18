package com.mnb.reminderapp.presentation

interface ItemActions {
    fun itemAdd(reminderItem: ReminderItem)
    fun itemProgress(reminderItem: ReminderItem)
    fun itemComplete(reminderItem: ReminderItem)
    fun itemDelay(reminderItem: ReminderItem)
    fun itemSkip(reminderItem: ReminderItem)
}