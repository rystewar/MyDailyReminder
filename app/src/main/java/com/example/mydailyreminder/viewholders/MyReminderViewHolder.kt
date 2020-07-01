package com.example.mydailyreminder.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.mydailyreminder.data.dataclasses.Reminder
import com.example.mydailyreminder.databinding.ReminderListItemLayoutBinding

class MyReminderViewHolder(
    private val reminderListItemLayout: ReminderListItemLayoutBinding,
    private val reminderClickListener: MyReminderClickListener
): RecyclerView.ViewHolder(reminderListItemLayout.root) {

    // TODO: Look into data binding
    fun bind(reminder: Reminder) {
        reminderListItemLayout.reminderName.text = reminder.name
        reminderListItemLayout.onOffSwitch.isChecked = reminder.isEnabled

        reminderListItemLayout.onOffSwitch.setOnClickListener {
            reminderClickListener.onToggled(reminder)
        }

        reminderListItemLayout.root.setOnClickListener {
            reminderClickListener.onReminderClicked(reminder)
        }
    }

    interface MyReminderClickListener {
        fun onToggled(reminder: Reminder)
        fun onReminderClicked(reminder: Reminder)
    }
}

