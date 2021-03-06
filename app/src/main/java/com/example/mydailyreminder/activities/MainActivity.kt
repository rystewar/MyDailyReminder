package com.example.mydailyreminder.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mydailyreminder.databinding.ActivityLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private lateinit var activityLayout: ActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLayout = ActivityLayoutBinding.inflate(layoutInflater)
        setContentView(activityLayout.root)
    }
}