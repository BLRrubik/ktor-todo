package ru.rubik.app.repository.user

import ru.rubik.app.model.User
import ru.rubik.app.request.user.UserRegisterRequest

interface UserRepository {
    suspend fun saveUser(request: UserRegisterRequest): User?
    suspend fun findById(id: Long): User?
    suspend fun findByUsername(username: String): User?
}