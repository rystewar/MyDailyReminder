package com.example.mydailyreminder.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.mydailyreminder.databinding.CreateReminderLayoutBinding
import com.example.mydailyreminder.dataclasses.Reminder
import com.example.mydailyreminder.dataclasses.ReminderFrequency
import com.example.mydailyreminder.exceptions.InvalidDataException
import com.example.mydailyreminder.viewmodels.CreateReminderViewModel

class CreateReminderFragment : Fragment() {

    private lateinit var createReminderLayout: CreateReminderLayoutBinding
    private val viewModel: CreateReminderViewModel by viewModels()

    // TODO: Edit text takes focus after changing orientation and opens the keyboard which is annoying. Investigate
    // TODO: Give more precise reminder frequency options

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createReminderLayout = CreateReminderLayoutBinding.inflate(inflater, container, false)
        return createReminderLayout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupBackIcon()
        setupSaveButton()
        setupFrequencyButtons()
    }

    private fun setupBackIcon() {
        createReminderLayout.headerBackIcon.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }

    private fun setupSaveButton() {
        createReminderLayout.headerSave.setOnClickListener {
            // TODO: Store reminder in DB and navigate back home
            // TODO: If any fields are empty, highlight them and alert the user
            try {
                viewModel.createReminder(
                    Reminder(
                        getReminderName(),
                        getReminderDescription(),
                        getReminderFrequency()
                    )
                )
            } catch (e: InvalidDataException) {
                Toast.makeText(context, "Missing required data", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupFrequencyButtons() {
        setupDailyFrequencyButton()
        setupWeeklyFrequencyButton()
        setupMonthlyFrequencyButton()
    }

    private fun setupDailyFrequencyButton() {
        createReminderLayout.dailyFrequencyButton.setOnClickListener {
            toggleFrequencyButton(it)
            resetFrequencyButton(createReminderLayout.weeklyFrequencyButton)
            resetFrequencyButton(createReminderLayout.monthlyFrequencyButton)
        }
    }

    private fun setupWeeklyFrequencyButton() {
        createReminderLayout.weeklyFrequencyButton.setOnClickListener {
            toggleFrequencyButton(it)
            resetFrequencyButton(createReminderLayout.dailyFrequencyButton)
            resetFrequencyButton(createReminderLayout.monthlyFrequencyButton)
        }
    }

    private fun setupMonthlyFrequencyButton() {
        createReminderLayout.monthlyFrequencyButton.setOnClickListener {
            toggleFrequencyButton(it)
            resetFrequencyButton(createReminderLayout.dailyFrequencyButton)
            resetFrequencyButton(createReminderLayout.weeklyFrequencyButton)
        }
    }

    private fun toggleFrequencyButton(button: View) {
        button.isSelected = !button.isSelected
    }

    private fun resetFrequencyButton(button: View) {
        button.isSelected = false
    }

    private fun getReminderName(): String {
        val name = createReminderLayout.reminderNameEditText.text.toString()

        if (!name.isBlank()) {
            return name
        } else {
            throw InvalidDataException()
        }
    }

    private fun getReminderDescription(): String {
        return createReminderLayout.reminderDescriptionEditText.text.toString()
    }

    private fun getReminderFrequency(): ReminderFrequency {
        val daily = createReminderLayout.dailyFrequencyButton.isSelected
        val weekly = createReminderLayout.weeklyFrequencyButton.isSelected
        val monthly = createReminderLayout.monthlyFrequencyButton.isSelected

        if (daily xor weekly xor monthly) {
            return ReminderFrequency(daily, weekly, monthly)
        } else {
            throw InvalidDataException()
        }
    }
}