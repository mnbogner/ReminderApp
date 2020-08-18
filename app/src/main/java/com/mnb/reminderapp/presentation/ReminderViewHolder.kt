package com.mnb.reminderapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mnb.reminderapp.R

class ReminderViewHolder(val itemActions: ItemActions, layoutInflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(layoutInflater.inflate(R.layout.list_item, parent, false)) {
    private var itemTitle: TextView? = null
    private var itemText: TextView? = null
    private var itemProgress: Button? = null
    private var itemComplete: Button? = null
    private var itemDelay: Button? = null
    private var itemSkip: Button? = null

    init {
        itemTitle = itemView.findViewById(R.id.item_title)
        itemText = itemView.findViewById(R.id.item_text)
        itemProgress = itemView.findViewById(R.id.progress_button)
        itemComplete = itemView.findViewById(R.id.complete_button)
        itemDelay = itemView.findViewById(R.id.delay_button)
        itemSkip = itemView.findViewById(R.id.skip_button)
    }

    fun bind(reminderItem: ReminderItem) {
        itemTitle?.text = reminderItem.title
        itemText?.text = reminderItem.text
        // TODO: move logic out of view holder?
        // can't postpone more than twice
        if (reminderItem.offset > 1) {
            itemProgress?.isEnabled = false
            itemProgress?.setBackgroundResource(R.drawable.ic_play_arrow_grey_18dp)
        } else {
            itemProgress?.setOnClickListener {
                itemActions.itemProgress(reminderItem)
            }
        }
        itemComplete?.setOnClickListener {
            itemActions.itemComplete(reminderItem)
        }
        // TODO: move logic out of view holder?
        // can't postpone more than twice
        if (reminderItem.offset > 1) {
            itemDelay?.isEnabled = false
            itemDelay?.setBackgroundResource(R.drawable.ic_pause_grey_18dp)
        } else {
            itemDelay?.setOnClickListener {
                itemActions.itemDelay(reminderItem)
            }
        }
        itemSkip?.setOnClickListener {
            itemActions.itemSkip(reminderItem)
        }
    }
}