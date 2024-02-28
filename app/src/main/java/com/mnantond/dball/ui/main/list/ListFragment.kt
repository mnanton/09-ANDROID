package com.mnantond.dball.ui.main.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.mnantond.dball.databinding.FragmentListBinding
import com.mnantond.dball.ui.login.LoginViewModel
import com.mnantond.dball.ui.main.SharedViewModel
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    val sharedViewModel: SharedViewModel by activityViewModels()

    private val adapter = HeroesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    //TODO Acciones con las Vistas
    }

    fun setObservers(){
        lifecycleScope.launch {
            Log.i("ListFragment",  "Start setObserver")
            sharedViewModel.uiStateList.collect {
                when(it){
                    is SharedViewModel.StateList.Idle-> TODO()
                    is SharedViewModel.StateList.Loading-> TODO()
                    is SharedViewModel.StateList.HeroIsSelected-> TODO()
                    is SharedViewModel.StateList.HeroIsUpdated-> TODO()
                    is SharedViewModel.StateList.Error-> TODO()
                }
            }
        }
    }
}