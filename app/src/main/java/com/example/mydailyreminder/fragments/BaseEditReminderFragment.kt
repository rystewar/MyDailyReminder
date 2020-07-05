package com.example.mydailyreminder.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.mydailyreminder.data.dataclasses.ReminderFrequency
import com.example.mydailyreminder.databinding.EditReminderLayoutBinding
import com.example.mydailyreminder.exceptions.InvalidDataException
import com.example.mydailyreminder.viewmodels.EditReminderViewModel
import dagger.hilt.android.AndroidEntryPoint

//TODO: This class should be abstract but there's an issue with hilt when using @AndroidEntryPoint on an abstract class (https://github.com/google/dagger/issues/1955)
@AndroidEntryPoint
open class BaseEditReminderFragment: Fragment() {

    protected lateinit var editReminderLayout: EditReminderLayoutBinding
    protected val viewModel: EditReminderViewModel by viewModels()

    //TODO: These should be abstract - See comment above
    open fun getHeaderTitle(): String = ""
    open fun onSaveClicked() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editReminderLayout = EditReminderLayoutBinding.inflate(inflater, container, false)
        return editReminderLayout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupHeader()
        setupBackIcon()
        setupSaveButton()
        setupFrequencyButtons()
    }

    private fun setupHeader() {
        editReminderLayout.headerTitle.text = getHeaderTitle()
    }

    private fun setupBackIcon() {
        editReminderLayout.headerBackIcon.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }

    private fun setupSaveButton() {
        editReminderLayout.headerSave.setOnClickListener {
            onSaveClicked()
        }
    }

    private fun setupFrequencyButtons() {
        setupDailyFrequencyButton()
        setupWeeklyFrequencyButton()
        setupMonthlyFrequencyButton()
    }

    private fun setupDailyFrequencyButton() {
        editReminderLayout.dailyFrequencyButton.setOnClickListener {
            toggleFrequencyButton(it)
            resetFrequencyButton(editReminderLayout.weeklyFrequencyButton)
            resetFrequencyButton(editReminderLayout.monthlyFrequencyButton)
        }
    }

    private fun setupWeeklyFrequencyButton() {
        editReminderLayout.weeklyFrequencyButton.setOnClickListener {
            toggleFrequencyButton(it)
            resetFrequencyButton(editReminderLayout.dailyFrequencyButton)
            resetFrequencyButton(editReminderLayout.monthlyFrequencyButton)
        }
    }

    private fun setupMonthlyFrequencyButton() {
        editReminderLayout.monthlyFrequencyButton.setOnClickListener {
            toggleFrequencyButton(it)
            resetFrequencyButton(editReminderLayout.dailyFrequencyButton)
            resetFrequencyButton(editReminderLayout.weeklyFrequencyButton)
        }
    }

    protected fun toggleFrequencyButton(button: View) {
        button.isSelected = !button.isSelected
    }

    private fun resetFrequencyButton(button: View) {
        button.isSelected = false
    }

    protected fun getReminderName(): String {
        val name = editReminderLayout.reminderNameEditText.text.toString()

        if (!name.isBlank()) {
            return name
        } else {
            throw InvalidDataException()
        }
    }

    protected fun getReminderDescription(): String {
        return editReminderLayout.reminderDescriptionEditText.text.toString()
    }

    protected fun getReminderFrequency(): ReminderFrequency {
        val daily = editReminderLayout.dailyFrequencyButton.isSelected
        val weekly = editReminderLayout.weeklyFrequencyButton.isSelected
        val monthly = editReminderLayout.monthlyFrequencyButton.isSelected

        if (daily xor weekly xor monthly) {
            return ReminderFrequency(daily, weekly, monthly)
        } else {
            throw InvalidDataException()
        }
    }

    protected fun showDuplicateReminderModal(
        onReplaceClickListener: DialogInterface.OnClickListener,
        onBackClickListener: DialogInterface.OnClickListener
    ) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Error")
            .setMessage("A reminder with this name already exists. Would you like to replace it?")
            .setPositiveButton("Replace", onReplaceClickListener )
            .setNegativeButton("Back", onBackClickListener)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}