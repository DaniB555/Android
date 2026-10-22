package com.example.loginpage.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.loginpage.utils.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ProfileUiState(
    val userId: Long = -1,
    val username: String? = null,
    val email: String? = null
)

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val sessionManager = SessionManager.getInstance(application)

    private val _uiState = MutableStateFlow(loadSession())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun refresh() {
        _uiState.value = loadSession()
    }

    private fun loadSession() = ProfileUiState(
        userId = sessionManager.userId,
        username = sessionManager.username,
        email = sessionManager.email
    )
}
