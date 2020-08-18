package com.mnb.reminderapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ReminderAdapter(private val itemActions: ItemActions, private val reminderList: List<ReminderItem>) :
    RecyclerView.Adapter<ReminderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ReminderViewHolder(itemActions, layoutInflater, parent)
    }

    override fun getItemCount(): Int {
        return reminderList.size
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val reminderItem = reminderList.get(position)
        holder.bind(reminderItem)
    }
}