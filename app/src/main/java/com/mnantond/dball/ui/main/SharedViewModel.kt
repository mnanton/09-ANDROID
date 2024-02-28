package com.mnantond.dball.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.mnantond.dball.data.ResourcesAPI
import com.mnantond.dball.domain.models.Hero
import com.mnantond.dball.domain.models.HeroDto
import com.mnantond.dball.ui.login.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class SharedViewModel: ViewModel() {

    private val _uiStateList = MutableStateFlow<SharedViewModel.StateList>(SharedViewModel.StateList.Idle())
    val uiStateList: StateFlow<SharedViewModel.StateList> =_uiStateList

    var heroList = mutableListOf<Hero>()
    private var heroListDto = mutableListOf<HeroDto>()

    sealed class StateList {
        class Idle: SharedViewModel.StateList()
        class Loading(val show: Boolean) : SharedViewModel.StateList()
        class HeroIsSelected(val hero: Hero) : SharedViewModel.StateList()
        class HeroIsUpdated(val hero: Hero) : SharedViewModel.StateList()
        class Error(val errorInfo: String) : SharedViewModel.StateList()
    }
        fun getHeroes(token: String) {
            Log.i("SharedViewModel", "START getHeroes")
            viewModelScope.launch(Dispatchers.IO) {
                if (heroListDto.isEmpty()) {
                    val client = OkHttpClient()
                    val url = "${ResourcesAPI.base_Url}${ResourcesAPI.base_HEroes}"
                    val formBody = FormBody.Builder()
                        .add("name", "")
                        .build()
                    val request = Request.Builder()
                        .url(url)
                        .post(formBody)
                        .addHeader("Authorization", "Bearer $token")
                        .build()
                    val call = client.newCall(request)
                    val response = call.execute()
                    if (response.isSuccessful) {
                        Log.i("SharedViewModel", "Response is Correct")
                        try {
                            //val jsonResponse = response.body?.string()
                            //heroList = Gson().fromJson(jsonResponse, Array<HeroDto>::class.java).toMutableList()
                            heroListDto =
                                Gson().fromJson(response.body?.string(), Array<HeroDto>::class.java)
                                    .toMutableList()
                            heroList = heroListDto.map {
                                Hero(it.id, it.photo, it.name)
                            }.toMutableList()
                            Log.i("SharedViewModel", "HeroListDto Values: ${heroListDto}")
                            Log.i("SharedViewModel", "HeroList Values: ${heroList}")
                        } catch (ex: Exception) {
                            Log.i("SharedViewModel", "Exception GSON: ${ex.message.toString()}")
                            //   _stateHeroes.value = StateHeroes.Error(ex.message.toString())
                        }
                    } else {
                        Log.i(
                            "SharedViewModel",
                            "Response is wrong, Error description: ${response.message}"
                        )
                        //_stateHeroes.value = StateHeroes.Error(response.message)
                    }
                }
            }
        }
}