package com.example.loginpage.network.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    val statusCode: Int? = null,
    val message: String? = null,
    val error: String? = null
)
