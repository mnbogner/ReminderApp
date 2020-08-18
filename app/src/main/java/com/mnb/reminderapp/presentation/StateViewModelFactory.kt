package com.mnb.reminderapp.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mnb.reminderapp.usecase.Interactors

class StateViewModelFactory(val application: Application, val interactors: Interactors) : ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        // TODO: implement injection for interactors
        return StateViewModel(application, interactors) as T
    }
}