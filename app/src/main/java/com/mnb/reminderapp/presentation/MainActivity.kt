package com.mnb.reminderapp.presentation

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mnb.reminderapp.R
import com.mnb.reminderapp.data.ReminderRepository
import com.mnb.reminderapp.data.ScoreboardRepository
import com.mnb.reminderapp.framework.ReminderRoomDataSource
import com.mnb.reminderapp.framework.ScoreboardPreferencesDataSource
import com.mnb.reminderapp.usecase.Interactors
import com.mnb.reminderapp.usecase.interactors.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), ItemActions {

    private val formatString = "EEEE, MMMM dd, yyyy"

    private lateinit var stateModel: StateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        date_edit.inputType = InputType.TYPE_NULL
        date_edit.setOnClickListener {
            val calendar = Calendar.getInstance()
            var day = calendar.get(Calendar.DAY_OF_MONTH)
            var month = calendar.get(Calendar.MONTH)
            var year = calendar.get(Calendar.YEAR)
            val dialog = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val dateFormat = SimpleDateFormat(formatString)
                    calendar.set(year, month, dayOfMonth)
                    date_edit.setText(dateFormat.format(calendar.time))
                }, year, month, day)
            dialog.show()
        }
        add_button.setOnClickListener {
            var itemTitle = title_edit.text.toString()
            var itemText = text_edit.text.toString()
            var itemDate = date_edit.text.toString()
            var dateObject = SimpleDateFormat(formatString).parse(itemDate);
            if (itemTitle.isEmpty() || itemText.isEmpty() || itemDate.isEmpty()) {
                return@setOnClickListener
            } else {
                itemAdd(ReminderItem(itemTitle, itemText, dateObject.time, 0))
                title_edit.text.clear()
                text_edit.text.clear()
                date_edit.text.clear()
            }
        }
        toggle_button.setOnClickListener {
            stateModel.handleEvent(Event.ToggleShowToday())
        }
        reminder_list.layoutManager = LinearLayoutManager(this)

        // TODO: set up conditional to determine which repositories are used
        // val reminderRepository = ReminderRepository(ReminderMemoryDataSource())
        // val scoreboardRepository = ScoreboardRepository(ScoreboardMemoryDataSource())
        val reminderRepository = ReminderRepository(ReminderRoomDataSource(this))
        val scoreboardRepository = ScoreboardRepository(ScoreboardPreferencesDataSource(this))

        // TODO: inject interactors in view model factory?
        stateModel = ViewModelProvider(this,
            StateViewModelFactory(application,
                Interactors(
                    GetReminders(reminderRepository),
                    ReminderAdd(reminderRepository),
                    ReminderProgress(reminderRepository),
                    ReminderComplete(reminderRepository),
                    ReminderDelay(reminderRepository),
                    ReminderSkip(reminderRepository),
                    GetScore(scoreboardRepository),
                    ScoreProgress(scoreboardRepository),
                    ScoreComplete(scoreboardRepository),
                    ScoreDelay(scoreboardRepository),
                    ScoreSkip(scoreboardRepository)
                )
            )
        ).get(StateViewModel::class.java)
        stateModel.state.observe(this, Observer {

            // TODO: reuse existing adapter?
            reminder_list.adapter = ReminderAdapter(this, it.itemList)
            score_text.text = "" + it.score + "pts"
            if (it.showToday) {
                toggle_button.setText("TODAY")
            } else {
                toggle_button.setText("ALL")
            }
        })
        stateModel.handleEvent(Event.Init())
    }

    override fun itemAdd(reminderItem: ReminderItem) {
        stateModel.handleEvent(Event.ItemAdd(reminderItem))
    }

    override fun itemProgress(reminderItem: ReminderItem) {
        stateModel.handleEvent(Event.ItemProgress(reminderItem))
    }

    override fun itemComplete(reminderItem: ReminderItem) {
        stateModel.handleEvent(Event.ItemComplete(reminderItem))
    }

    override fun itemDelay(reminderItem: ReminderItem) {
        stateModel.handleEvent(Event.ItemDelay(reminderItem))
    }

    override fun itemSkip(reminderItem: ReminderItem) {
        stateModel.handleEvent(Event.ItemSkip(reminderItem))
    }
}