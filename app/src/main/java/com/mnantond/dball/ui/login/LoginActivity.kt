package com.mnantond.dball.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mnantond.dball.R
import com.mnantond.dball.databinding.ActivityLoginBinding
import com.mnantond.dball.ui.main.MainActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    val viewModel:LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setObservers()
        setListener()
    }

    fun setListener(){
        binding.btnLogin.setOnClickListener {viewModel.launchLogin(binding.etxtUsuario.text.toString(),binding.etxtPassword.text.toString())}
    }
    fun setObservers(){
        lifecycleScope.launch {
            Log.i("setObserver",  "Start setObserver")
            viewModel.uiState.collect {
                when(it){
                    is LoginViewModel.State.Idle-> idle()
                    is LoginViewModel.State.Loading-> showLoading(true)
                    is LoginViewModel.State.SuccessLogin->{
                        Log.i("setObserver",  "State SuccessLogin")
                        showLoading(false)
                        goMainActivity()
                    }
                    is LoginViewModel.State.OnError->showLoading(false)
                }
            }
        }
    }

    fun idle(){
        binding.pBarLogin.visibility = View.GONE
    }

    fun showLoading(show: Boolean){
        binding.pBarLogin.visibility = if (show)
            View.VISIBLE
        else
            View.GONE
    }

    fun goMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}