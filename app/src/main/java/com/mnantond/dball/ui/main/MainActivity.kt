package com.mnantond.dball.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mnantond.dball.data.ResourcesAPI
import com.mnantond.dball.databinding.ActivityMainBinding
import com.mnantond.dball.ui.main.detail.DetailFragment
import com.mnantond.dball.ui.main.list.ListFragment


class MainActivity: AppCompatActivity()  {

    private lateinit var binding: ActivityMainBinding
    val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadHeroesFragment()
    }

    private fun loadHeroesFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentMain.id,ListFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun loadDetailFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentMain.id,DetailFragment())
            .addToBackStack(null)
            .commit()
    }
}