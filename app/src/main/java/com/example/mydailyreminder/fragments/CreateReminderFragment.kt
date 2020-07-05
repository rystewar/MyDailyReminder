package com.example.mydailyreminder.fragments

import android.content.DialogInterface
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mydailyreminder.data.dataclasses.Reminder
import com.example.mydailyreminder.exceptions.InvalidDataException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateReminderFragment : BaseEditReminderFragment() {

    override fun getHeaderTitle(): String {
        return "Create Reminder"
    }

    override fun onSaveClicked() {
        Log.d("@@@", "Save Clicked")
        // TODO: If any fields are empty, highlight them and alert the user
        CoroutineScope(IO).launch {
            val reminder = Reminder(
                getReminderName(),
                getReminderDescription(),
                getReminderFrequency(),
                true
            )

            if (reminderExists(reminder)) {
                // TODO: Make this modal look nice
                val replaceClickListener =  DialogInterface.OnClickListener { _, _ ->
                    CoroutineScope(IO).launch {
                        createReminder(reminder)
                    }
                }
                val backClickListener = DialogInterface.OnClickListener { _, _ ->
                    Log.d("@@@", "On back clicked")
                }

                withContext(Main) {
                    showDuplicateReminderModal(replaceClickListener, backClickListener)
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
                findNavController().popBackStack()
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