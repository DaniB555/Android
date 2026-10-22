package com.example.loginpage.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokensDto(
    @SerialName("access_token") val accessTokenSnake: String? = null,
    val accessToken: String? = null,
    @SerialName("refresh_token") val refreshTokenSnake: String? = null,
    val refreshToken: String? = null
) {
    val effectiveAccessToken: String? get() = accessTokenSnake ?: accessToken
    val effectiveRefreshToken: String? get() = refreshTokenSnake ?: refreshToken
}
