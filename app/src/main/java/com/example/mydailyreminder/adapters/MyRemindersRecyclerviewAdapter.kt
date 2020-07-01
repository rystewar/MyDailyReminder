package com.example.mydailyreminder.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mydailyreminder.data.dataclasses.Reminder
import com.example.mydailyreminder.databinding.ReminderListItemLayoutBinding
import com.example.mydailyreminder.viewholders.MyReminderViewHolder

class MyRemindersRecyclerviewAdapter(
    private val reminderList: List<Reminder>,
    private val reminderClickListener: MyReminderViewHolder.MyReminderClickListener
): RecyclerView.Adapter<MyReminderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyReminderViewHolder {
        return MyReminderViewHolder(ReminderListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false), reminderClickListener)
    }

    override fun onBindViewHolder(holder: MyReminderViewHolder, position: Int) {
        holder.bind(reminderList[position])
    }

    override fun getItemCount(): Int {
        return reminderList.size
    }
}