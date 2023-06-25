package ru.rubik.app.repository.user

import ru.rubik.app.entity.UserEntity
import ru.rubik.app.request.user.UserRegisterRequest

interface UserRepository {
    suspend fun saveUser(request: UserRegisterRequest): UserEntity
    suspend fun findById(id: Long): UserEntity?
    suspend fun findByUsername(username: String): UserEntity?
}