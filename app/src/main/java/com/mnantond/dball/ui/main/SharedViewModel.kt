package com.mnantond.dball.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.mnantond.dball.data.ResourcesAPI
import com.mnantond.dball.domain.models.Hero
import com.mnantond.dball.domain.models.HeroDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class SharedViewModel: ViewModel() {

    private var heroList = mutableListOf<HeroDto>()

    fun getHeroes(token: String) {
        Log.i("SharedViewModel", "START getHeroes")
        viewModelScope.launch(Dispatchers.IO) {
            if (heroList.isEmpty()) {
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
                        heroList = Gson().fromJson(response.body?.string(),Array<HeroDto>::class.java).toMutableList()
                        Log.i("SharedViewModel", "Hero List: ${heroList}")
                    } catch (ex: Exception) {
                        Log.i("SharedViewModel", "Excepcion GSON: ${ex.message.toString()}")
                    //   _stateHeroes.value = StateHeroes.Error(ex.message.toString())
                    }
                } else {
                    Log.i("SharedViewModel", "Error: ${response.message}")
                    //_stateHeroes.value = StateHeroes.Error(response.message)
                }
            }
        }
    }
}