package com.example.mydailyreminder.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.mydailyreminder.data.dataclasses.Reminder
import com.example.mydailyreminder.exceptions.InvalidDataException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditReminderFragment: BaseEditReminderFragment() {
    val args: EditReminderFragmentArgs by navArgs()

    lateinit var reminder: Reminder

    override fun getHeaderTitle(): String {
        return "Edit Reminder"
    }

    override fun onSaveClicked() {
        // TODO: If any fields are empty, highlight them and alert the user
        // TODO: If user attempts to change reminder name, ensure the old reminder is deleted
        CoroutineScope(Dispatchers.IO).launch {
            try {
                viewModel.createOrUpdateReminder(
                    Reminder(
                        getReminderName(),
                        getReminderDescription(),
                        getReminderFrequency(),
                        true
                    )
                )

                withContext(Dispatchers.Main) {
                    // TODO: Indicate reminder was saved successfully (Without Toast) then pop backstack
                    Toast.makeText(context, "Reminder created!", Toast.LENGTH_LONG).show()
                }
            } catch (e: InvalidDataException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Missing required data", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        reminder = args.reminder
        super.onViewCreated(view, savedInstanceState)
        populateFields()
    }

    private fun populateFields() {
        populateReminderNameField()
        populateReminderDescriptionField()
        toggleCorrectFrequencyButton()
    }

    private fun populateReminderNameField() {
        editReminderLayout.reminderNameEditText.setText(reminder.name)
    }

    private fun populateReminderDescriptionField() {
        editReminderLayout.reminderDescriptionEditText.setText(reminder.description)
    }

    private fun toggleCorrectFrequencyButton() {
        when {
            reminder.frequency.daily -> toggleFrequencyButton(editReminderLayout.dailyFrequencyButton)
            reminder.frequency.weekly -> toggleFrequencyButton(editReminderLayout.weeklyFrequencyButton)
            reminder.frequency.monthly -> toggleFrequencyButton(editReminderLayout.monthlyFrequencyButton)
        }
    }
}