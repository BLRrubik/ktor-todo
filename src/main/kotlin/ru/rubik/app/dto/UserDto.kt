package ru.rubik.app.dto

data class UserDto(
    val id: Long,
    val username: String
)

data class AuthUserDto (
    val id: Long,
    val username: String,
    val authToken: String
)