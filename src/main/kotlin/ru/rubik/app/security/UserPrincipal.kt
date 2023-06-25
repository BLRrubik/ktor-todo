package ru.rubik.app.security

import io.ktor.server.auth.*

data class UserPrincipal(val id: Long): Principal