package com.mnantond.dball.ui.main.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.mnantond.dball.R
import com.mnantond.dball.data.ResourcesAPI
import com.mnantond.dball.databinding.FragmentListBinding
import com.mnantond.dball.ui.main.SharedViewModel

class ListFragment : Fragment() {
    val sharedViewModel: SharedViewModel by viewModels()
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    binding = FragmentListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.getHeroes(ResourcesAPI.api_token)
    }
}