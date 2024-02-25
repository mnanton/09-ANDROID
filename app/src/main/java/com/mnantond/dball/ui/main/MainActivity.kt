package com.mnantond.dball.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mnantond.dball.databinding.ActivityMainBinding


class MainActivity: AppCompatActivity()  {

    private lateinit var binding: ActivityMainBinding
    val viewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}