package com.example.profile

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profile.data.api.ApiClient
import com.example.profile.data.api.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun createNewUser(user : UserApi){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.apiService.createNewUser(user)
                Log.i("MainViewModel", response.toString())
            } catch (e: Exception){

                when(e){
                    is HttpException -> {
                        Log.i("MainViewModel", e.toString())
                    } else -> {
                        Log.i("MainViewModel", "Error. Contacte al soporte tecnico")
                    }
                }
            }
        }
    }
}