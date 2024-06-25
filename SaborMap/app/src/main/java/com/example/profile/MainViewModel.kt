package com.example.profile

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profile.data.api.ApiClient
import com.example.profile.data.api.CommentApi
import com.example.profile.data.api.LoginApi
import com.example.profile.data.api.UserApi
import com.example.profile.model.SuggestionDataModel
import com.example.profile.model.SuggestionList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel : ViewModel() {

    // Api

    private val _uiState = MutableStateFlow<UiState>(UiState.Ready)
    val uiState: StateFlow<UiState> = _uiState

    private val _comments = MutableStateFlow<List<CommentApi>>(emptyList())
    val comments: StateFlow<List<CommentApi>> = _comments

    private val api = ApiClient.apiService

    // Home y Favorites
    /*

    private val _suggestions = mutableStateListOf<SuggestionDataModel>()
    val suggestions: List<SuggestionDataModel> get() = _suggestions

    private val _favorites = mutableStateListOf<SuggestionDataModel>()
    val favorites: List<SuggestionDataModel> get() = _favorites

    init {
        _suggestions.addAll(SuggestionList)
    }

    fun toggleFavorite(suggestion: SuggestionDataModel) {
        suggestion.isFavorite = !suggestion.isFavorite
        if (suggestion.isFavorite) {
            _favorites.add(suggestion)
        } else {
            _favorites.remove(suggestion)
        }
    }

     */

    val suggestionList = mutableStateListOf<SuggestionDataModel>(
        // Inicializa tu lista con datos
        SuggestionDataModel(1, R.drawable.burgerking, "Ratings", 4.0, 700, 700, false),
        SuggestionDataModel(2, R.drawable.starbucks, "Ratings", 4.5, 800, 800, false),
        SuggestionDataModel(3, R.drawable.pollocampero, "Ratings", 3.5, 900, 900, false)
    )
    val favoriteList = mutableStateListOf<SuggestionDataModel>()

    fun updateFavorite(suggestion: SuggestionDataModel) {
        suggestion.isFavorite = !suggestion.isFavorite
        if (suggestion.isFavorite) {
            favoriteList.add(suggestion)
        } else {
            favoriteList.remove(suggestion)
        }
    }

    // Api

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

    fun postComment(identifier: String, comment: CommentApi) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading
                val response = api.postComment(identifier, comment)
                Log.i("MainViewModel", response.toString())
                _uiState.value = UiState.Success("Comment posted successfully")
                fetchComments(identifier) // Refresh comments after posting
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

    fun fetchComments(identifier: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading
                val response = api.getComments(identifier)
                _comments.value = response
                _uiState.value = UiState.Ready
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