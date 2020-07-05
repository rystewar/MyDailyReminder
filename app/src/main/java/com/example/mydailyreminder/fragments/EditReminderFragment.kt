package com.example.mydailyreminder.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mydailyreminder.data.dataclasses.Reminder
import com.example.mydailyreminder.exceptions.InvalidDataException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//TODO: Enable save button after something's been changed
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
            val editedReminder = Reminder(
                getReminderName(),
                getReminderDescription(),
                getReminderFrequency(),
                true
            )

            when {
                reminder.name == editedReminder.name -> {
                    createOrUpdateReminder(editedReminder)
                    findNavController().popBackStack()
                }
                reminder.name != editedReminder.name && viewModel.reminderExists(editedReminder) -> {
                    val replaceClickListener = DialogInterface.OnClickListener { _, _ ->
                        CoroutineScope(Dispatchers.IO).launch {
                            viewModel.deleteReminder(reminder)
                            createOrUpdateReminder(editedReminder)
                            findNavController().popBackStack()
                        }
                    }
                    val backClickListener = DialogInterface.OnClickListener { _, _ ->
                        Log.d("@@@", "Back Clicked")
                    }

                    withContext(Dispatchers.Main) {
                        showDuplicateReminderModal(replaceClickListener, backClickListener)
                    }
                }
                else -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.deleteReminder(reminder)
                        createOrUpdateReminder(editedReminder)
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }

    private suspend fun createOrUpdateReminder(editedReminder: Reminder) {
        try {
            viewModel.createOrUpdateReminder(
                editedReminder
            )

            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Reminder created!", Toast.LENGTH_LONG).show()
            }
        } catch (e: InvalidDataException) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Missing required data", Toast.LENGTH_LONG).show()
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