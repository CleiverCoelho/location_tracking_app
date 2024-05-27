package com.plcoding.backgroundlocationtracking.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intent.firebaseService.FirebaseController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(private val firebase: FirebaseController = FirebaseController())
    : ViewModel() {
    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    fun updateUserLogin (uid: String?, email: String?) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    uid = uid,
                    email = email,
                    isSignedIn = true
                )
            }
        }
    }
}