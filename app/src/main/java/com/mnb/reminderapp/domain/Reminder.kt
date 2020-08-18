package com.mnb.reminderapp.domain

data class Reminder(
    val title: String,
    val text: String,
    val date: Long,
    val repeat: Int,
    val offset: Int
) : Comparable<Reminder> {
    override fun compareTo(other: Reminder): Int {

        // sort first by date,
        // then by offset (so incomplete tasks get priority)
        // then by title (primary key, so must be unique)

        if (this.date != other.date) {
            // lowest dates first
            return this.date.compareTo(other.date)
        } else if (this.offset != other.offset) {
            // highest offsets first
            return other.offset.compareTo(this.offset)
        } else {
            // lowest titles first
            return this.title.compareTo(other.title)
        }
    }
}