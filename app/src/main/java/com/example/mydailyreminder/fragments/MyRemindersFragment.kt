package com.example.mydailyreminder.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydailyreminder.adapters.MyRemindersRecyclerviewAdapter
import com.example.mydailyreminder.data.dataclasses.Reminder
import com.example.mydailyreminder.databinding.MyRemindersLayoutBinding
import com.example.mydailyreminder.viewholders.MyReminderViewHolder
import com.example.mydailyreminder.viewmodels.MyRemindersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


// TODO: Order list (Eventually I'd like to have multiple sort by methods (Name, Frequency, Next up))
// TODO: There seems to be a bug where the toggle states aren't correct (Exit app with 4 enabled, come back with 5 enabled)

@AndroidEntryPoint
class MyRemindersFragment : Fragment(), MyReminderViewHolder.MyReminderClickListener {

    private val viewModel: MyRemindersViewModel by viewModels()
    private lateinit var myRemindersLayout: MyRemindersLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myRemindersLayout = MyRemindersLayoutBinding.inflate(inflater, container, false)
        return myRemindersLayout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupBackIcon()
    }

    private fun setupRecyclerView() {
        CoroutineScope(IO).launch {
            val reminders = viewModel.getReminders()

            withContext(Main) {
                populateRecyclerview(reminders)
            }
        }
    }

    private fun setupBackIcon() {
        myRemindersLayout.headerBackIcon.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }

    private fun populateRecyclerview(reminders: List<Reminder>) {
        myRemindersLayout.reminderList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = MyRemindersRecyclerviewAdapter(sortAlphabetically(reminders), this@MyRemindersFragment)
        }
    }

    private fun sortAlphabetically(reminders: List<Reminder>): List<Reminder> {
        return reminders.sortedBy { it.name }
    }

    override fun onToggled(reminder: Reminder) {
        reminder.apply { isEnabled = !isEnabled }
        CoroutineScope(IO).launch {
            viewModel.updateReminder(reminder)
        }
    }

    override fun onReminderClicked(reminder: Reminder) {
        val action = MyRemindersFragmentDirections.actionMyRemindersFragmentToEditReminderFragment(reminder)
        findNavController().navigate(action)
    }
}