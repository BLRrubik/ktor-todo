package ru.rubik.app.request.user

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRequest (
    val username: String,
    val password: String
)