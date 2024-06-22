package com.example.profile

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profile.data.api.ApiClient
import com.example.profile.data.api.LoginApi
import com.example.profile.data.api.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Ready)
    val uiState: StateFlow<UiState> = _uiState

    private val api = ApiClient.apiService

    fun createNewUser(user: UserApi) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading
                val response = api.createNewUser(user)
                Log.i("MainViewModel", response.toString())
                _uiState.value = UiState.Success("Registered successfully")
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                Log.e("MainViewModel", "HTTP error: ${e.message()} - $errorBody", e)
                _uiState.value = UiState.Error(e.message())
            } catch (e: Exception) {
                Log.e("MainViewModel", "Unknown error", e)
                _uiState.value = UiState.Error("Error. Contact support.")
            }
        }
    }

    fun logIn(login: LoginApi){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading
                val response = api.logIn(login)
                Log.i("MainViewModel", response.toString())
                _uiState.value = UiState.Success("Login successfully")
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                Log.e("MainViewModel", "HTTP error: ${e.message()} - $errorBody", e)
                _uiState.value = UiState.Error(e.message())
            } catch (e: Exception) {
                Log.e("MainViewModel", "Unknown error", e)
                _uiState.value = UiState.Error("Error. Contact support.")
            }
        }
    }

    fun setStateToReady() {
        _uiState.value = UiState.Ready
    }
}

sealed class UiState {
    object Loading : UiState()
    object Ready : UiState()
    data class Success(val msg: String) : UiState()
    data class Error(val msg: String) : UiState()
}
