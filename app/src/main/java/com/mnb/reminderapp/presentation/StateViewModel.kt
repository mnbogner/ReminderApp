package com.mnb.reminderapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mnb.reminderapp.domain.Reminder
import com.mnb.reminderapp.usecase.Interactors
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class StateViewModel(application: Application, val interactors: Interactors) : AndroidViewModel(application) {

    private val formatString = "EEEE, MMMM dd, yyyy"

    val state = MutableLiveData<State>()

    // TODO: remove hard coded repeat value when repeat is implemented

    fun handleEvent(event: Event) {
        when(event) {
            is Event.Init -> {
                viewModelScope.launch {
                    var score = interactors.getScore.invoke()
                    var list = convertList(interactors.getReminders.invoke())
                    var showToday = state.value?.showToday ?: true
                    if (showToday) {
                        list = filterList(list)
                    }
                    state.postValue(State(score, list, showToday))
                }
            }
            is Event.ToggleShowToday -> {
                viewModelScope.launch {
                    var score = interactors.getScore.invoke()
                    var list = convertList(interactors.getReminders.invoke())
                    var showToday = !(state.value?.showToday ?: true)
                    if (showToday) {
                        list = filterList(list)
                    }
                    state.postValue(State(score, list, showToday))
                }
            }
            is Event.ItemAdd -> {
                viewModelScope.launch {
                    var score = interactors.getScore.invoke()
                    var list = convertList(interactors.reminderAdd.invoke(
                        Reminder(
                            event.item.title,
                            event.item.text,
                            event.item.date,
                            0,
                            event.item.offset
                        )
                    ))
                    var showToday = state.value?.showToday ?: true
                    if (showToday) {
                        list = filterList(list)
                    }
                    state.postValue(State(score, list, showToday))
                }
            }
            is Event.ItemProgress -> {
                viewModelScope.launch {
                    var score = interactors.scoreProgress.invoke()
                    var list = convertList(interactors.reminderProgress.invoke(
                        Reminder(
                            event.item.title,
                            event.item.text,
                            event.item.date,
                            0,
                            event.item.offset
                        )
                    ))
                    var showToday = state.value?.showToday ?: true
                    if (showToday) {
                        list = filterList(list)
                    }
                    state.postValue(State(score, list, showToday))
                }
            }
            is Event.ItemComplete -> {
                viewModelScope.launch {
                    var score = interactors.scoreComplete.invoke(event.item.offset)
                    var list = convertList(interactors.reminderComplete.invoke(
                        Reminder(
                            event.item.title,
                            event.item.text,
                            event.item.date,
                            0,
                            event.item.offset
                        )
                    ))
                    var showToday = state.value?.showToday ?: true
                    if (showToday) {
                        list = filterList(list)
                    }
                    state.postValue(State(score, list, showToday))
                }
            }
            is Event.ItemDelay -> {
                viewModelScope.launch {
                    var score = interactors.scoreDelay.invoke()
                    var list = convertList(interactors.reminderDelay.invoke(
                        Reminder(
                            event.item.title,
                            event.item.text,
                            event.item.date,
                            0,
                            event.item.offset
                        )
                    ))
                    var showToday = state.value?.showToday ?: true
                    if (showToday) {
                        list = filterList(list)
                    }
                    state.postValue(State(score, list, showToday))
                }
            }
            is Event.ItemSkip -> {
                viewModelScope.launch {
                    var score = interactors.scoreSkip.invoke(event.item.offset)
                    var list = convertList(interactors.reminderSkip.invoke(
                        Reminder(
                            event.item.title,
                            event.item.text,
                            event.item.date,
                            0,
                            event.item.offset
                        )
                    ))
                    var showToday = state.value?.showToday ?: true
                    if (showToday) {
                        list = filterList(list)
                    }
                    state.postValue(State(score, list, showToday))
                }
            }
        }
    }

    private fun convertList(remindersIn: List<Reminder>): List<ReminderItem> {
        var remindersOut = ArrayList<ReminderItem>()
        // sort list here according to Comparable implementation (by date, offset, title)
        remindersIn.sorted().forEach {
            remindersOut.add(ReminderItem(it.title, it.text, it.date, it.offset))
        }
        return remindersOut
    }

    // debated whether to include this logic here but i think filtering items
    // based on a ui toggle is more of a ui issue than a data issue
    private fun filterList(remindersIn: List<ReminderItem>): List<ReminderItem> {
        var remindersOut = ArrayList<ReminderItem>()
        // use format string to "truncate" date value
        val nowDate = Date()
        val nowString = SimpleDateFormat(formatString).format(nowDate)
        val todayDate = SimpleDateFormat(formatString).parse(nowString)
        remindersIn.forEach {
            if (it.date == todayDate.time) {
                remindersOut.add(it)
            }
        }
        return remindersOut
    }
}