package com.example.loginpage.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.loginpage.utils.SessionManager

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val sessionManager = SessionManager.getInstance(application)

    fun logout() {
        sessionManager.clear()
    }
}
