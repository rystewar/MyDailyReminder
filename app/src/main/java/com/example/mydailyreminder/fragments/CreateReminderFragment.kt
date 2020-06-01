package com.example.mydailyreminder.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mydailyreminder.databinding.CreateReminderLayoutBinding

class CreateReminderFragment: Fragment() {

    private lateinit var createReminderLayout: CreateReminderLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createReminderLayout = CreateReminderLayoutBinding.inflate(inflater, container, false)
        return createReminderLayout.root
    }
}