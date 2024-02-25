package com.mnantond.dball.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnantond.dball.data.ResourcesAPI
import com.mnantond.dball.data.ResourcesAPI.Companion.api_token
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.Credentials
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class LoginViewModel:ViewModel() {

    private val _uiState = MutableStateFlow<State>(State.Idle())
    val uiState: StateFlow<State> =_uiState

    sealed class State {
        class Idle : State()
        class Loading(val show: Boolean) : State()
        class SuccessLogin(val message: String) : State()
        class OnError(val errorInfo: String) : State()
    }
    fun launchLogin(userLogin: String, userPassword: String){
        viewModelScope.launch(Dispatchers.IO){
            _uiState.value = State.Loading(true)
            Log.i("APIdb",  "START apiLogin")
            apiLogin(userLogin,userPassword)
            Log.i("APIdb",  "END apiLogin")
        }
    }

    fun apiLogin (userLogin: String, userPassword: String){
        val client = OkHttpClient()
        val url = "${ResourcesAPI.base_Url}${ResourcesAPI.base_Login}"
        val credentials = Credentials.basic("${userLogin}", "${userPassword}")
        val formBody = FormBody.Builder()
            .build()
        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", credentials)
            .post(formBody)
            .build()
        val call = client.newCall(request)
        val response = call.execute()
        if (response.isSuccessful) {
            Log.i("APIdb",  "Response is Succesfully")
            response.body?.let {
                ResourcesAPI.api_token = (it.string())
                Log.i("APIdb",  "Token Value= ${ResourcesAPI.api_token.toString()}")
            } ?: "No Token"
        } else {
            Log.i("APIdb",  "Response is Error")
            ResourcesAPI.api_token = ""
        }
    }
}