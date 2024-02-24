package com.mnantond.dball.ui.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mnantond.dball.R
import com.mnantond.dball.databinding.ActivityLoginBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    val viewModel:LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListener()
        setObservers()
    }

    fun setListener(){
        binding.btnLogin.setOnClickListener {viewModel.login()}
    }
    fun setObservers(){
        lifecycleScope.launch {
            viewModel.uistate.collect {
                when(it){
                    is LoginViewModel.Idle-> { }
                    is LoginViewModel.Loading-> { }
                    is LoginViewModel.Sucess->{ }
                    is LoginViewModel.OnError->{ }
                }
            }
        }
    }
}