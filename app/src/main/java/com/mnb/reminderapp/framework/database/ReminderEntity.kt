package com.mnb.reminderapp.framework.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO: make title the primary key

@Entity(tableName = "reminders")
data class ReminderEntity(
    @PrimaryKey
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "repeat") val repeat: Int,
    @ColumnInfo(name = "offset") val offset: Int
) {
    // don't want an arbitrary primary key since we need to maintain
    // functionality across different data repository implementations
}