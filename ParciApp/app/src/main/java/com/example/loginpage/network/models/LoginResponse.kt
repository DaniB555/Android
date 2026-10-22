package com.example.loginpage.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val message: String? = null,
    val user: UserDto? = null,
    val tokens: TokensDto? = null,
    @SerialName("access_token") val rootAccessTokenSnake: String? = null,
    val accessToken: String? = null,
    @SerialName("refresh_token") val rootRefreshTokenSnake: String? = null,
    val refreshToken: String? = null
) {
    val effectiveAccessToken: String?
        get() = tokens?.effectiveAccessToken ?: rootAccessTokenSnake ?: accessToken

    val effectiveRefreshToken: String?
        get() = tokens?.effectiveRefreshToken ?: rootRefreshTokenSnake ?: refreshToken
}
