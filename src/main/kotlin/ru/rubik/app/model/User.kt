package ru.rubik.app.model

import kotlinx.serialization.Serializable

data class User (
    val id: Long,
    val username: String,
    val password: String
) {
    fun toDto(): UserDto {
        return UserDto(
            id, username
        )
    }
}

data class UserDto(
    val id: Long,
    val username: String,
)