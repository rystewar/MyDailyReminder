package com.example.mydailyreminder.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.widget.Toast
import com.example.mydailyreminder.R
import com.example.mydailyreminder.data.dataclasses.Reminder
import com.example.mydailyreminder.exceptions.InvalidDataException
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class CreateReminderFragment : BaseEditReminderFragment() {

    override fun getHeaderTitle(): String {
        return "Create Reminder"
    }

    override fun onSaveClicked() {
        Log.d("@@@", "Save Clicked")
        // TODO: If any fields are empty, highlight them and alert the user
        // TODO: If reminder with same name already exists, prompt to override it
        CoroutineScope(Dispatchers.IO).launch {
            val reminder = Reminder(
                getReminderName(),
                getReminderDescription(),
                getReminderFrequency(),
                true
            )

            if (reminderExists(reminder)) {
                Log.d("@@@", "Reminder already exists")
                // TODO: Tell user reminder already exists - Ask them if they want to override the old one

                // TODO: Make this modal look nice
                // TODO: If user wants to replace reminder, simply update the existing reminder with the new data
                // TODO: If the user chooses not to replace reminder, highlight name field to indicate what needs updated
                withContext(Main) {
                    val builder: AlertDialog.Builder? = activity?.let {
                        AlertDialog.Builder(it)
                    }

                    builder?.setMessage("A reminder with this name already exists. Would you want to replace it?")?.setTitle("Error")?.setPositiveButton("Replace", DialogInterface.OnClickListener { dialog, which ->
                        Log.d("@@@", "Positive button clicked")
                    })?.setNegativeButton("Back", DialogInterface.OnClickListener { dialog, which ->
                        Log.d("@@@", "Negative button clicked")
                    })

                    val dialog: AlertDialog? = builder?.create()
                    dialog?.show()
                }


            } else {
                createReminder(reminder)
            }
        }
    }

    private suspend fun reminderExists(reminder: Reminder): Boolean {
        return viewModel.reminderExists(reminder)
    }

    private suspend fun createReminder(reminder: Reminder) {
        try {
            viewModel.createOrUpdateReminder(reminder)

            withContext(Main) {
                // TODO: Indicate reminder was saved successfully (Without Toast)
                Toast.makeText(context, "Reminder created!", Toast.LENGTH_LONG).show()
            }
        } catch (e: InvalidDataException) {
            withContext(Main) {
                Toast.makeText(context, "Missing required data", Toast.LENGTH_LONG).show()
            }
        }
    }
}