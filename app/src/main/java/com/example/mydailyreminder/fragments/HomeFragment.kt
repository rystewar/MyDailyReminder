package com.example.mydailyreminder.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mydailyreminder.databinding.HomeLayoutBinding

class HomeFragment : Fragment() {

    private lateinit var homeLayout: HomeLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeLayout = HomeLayoutBinding.inflate(inflater, container, false)
        return homeLayout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    private fun setupButtons() {
//        setupFindRemindersButton()
        setupCreateRemindersButton()
        setupMyRemindersButton()
//        setupSettingsButton()
    }

    private fun setupFindRemindersButton() {
        homeLayout.findRemindersButton.setOnClickListener {
            // TODO
        }
    }

    private fun setupCreateRemindersButton() {
        homeLayout.createRemindersButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToCreateReminderFragment()
            it.findNavController().navigate(action)
        }
    }

    private fun setupMyRemindersButton() {
        homeLayout.editRemindersButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToMyRemindersFragment()
            it.findNavController().navigate(action)
        }
    }

    private fun setupSettingsButton() {
        homeLayout.settingsButton.setOnClickListener {
            // TODO
        }
    }
}