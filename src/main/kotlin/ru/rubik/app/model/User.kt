package ru.rubik.app.model

import kotlinx.serialization.Serializable

data class User (
    val id: Long,
    val username: String,
    var password: String
) {
    fun toDto(): UserDto {
        return UserDto(
            id, username
        )
    }

    fun toAuthDto(authToken: String): AuthUserDto {
        return AuthUserDto(
            id, username, authToken
        )
    }
}

data class UserDto(
    val id: Long,
    val username: String,
)

data class AuthUserDto (
    val id: Long,
    val username: String,
    val authToken: String
)