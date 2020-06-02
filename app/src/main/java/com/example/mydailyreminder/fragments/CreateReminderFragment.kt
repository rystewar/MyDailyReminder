package com.example.mydailyreminder.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mydailyreminder.databinding.CreateReminderLayoutBinding

class CreateReminderFragment : Fragment() {

    private lateinit var createReminderLayout: CreateReminderLayoutBinding

    // TODO: Edit text takes focus after changing orientation and opens the keyboard which is annoying. Investigate

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
        // TODO: Store reminder in DB and navigate back home
        // TODO: If any fields are empty alert the user
        createReminderLayout.headerSave.setOnClickListener {
            Toast.makeText(context, "Save Clicked", Toast.LENGTH_LONG).show()
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
}