package com.example.loginpage

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object MainGraph

@Serializable
object Home

@Serializable
data class Profile(
    val userId: String = "guest",
    val userName: String = "Guest"
)
