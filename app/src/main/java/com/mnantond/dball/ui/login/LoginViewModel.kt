package com.mnantond.dball.ui.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel:ViewModel() {

    private val _uistate = MutableStateFlow(Idle())
    val uistate: StateFlow<State> =_uistate

    sealed class State
        class Idle: State()
        class Loading: State()
        class Sucess: State()
        class OnError: State()

    fun login(){
        //TODO
    }
}