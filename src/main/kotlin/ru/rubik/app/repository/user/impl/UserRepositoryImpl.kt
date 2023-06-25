package ru.rubik.app.repository.user.impl

import org.jetbrains.exposed.sql.transactions.transaction
import ru.rubik.app.entity.UserEntity
import ru.rubik.app.entity.UsersTable
import ru.rubik.app.repository.user.UserRepository
import ru.rubik.app.request.user.UserRegisterRequest
import ru.rubik.app.security.encode

class UserRepositoryImpl: UserRepository {
    override suspend fun saveUser(request: UserRegisterRequest): UserEntity {
        return transaction {
            UserEntity.new {
                username = request.username
                password = encode(request.password)
            }
        }
    }

    override suspend fun findById(id: Long): UserEntity? {
        return transaction {
            UserEntity.findById(id)
        }
    }

    override suspend fun findByUsername(username: String): UserEntity? {
        return transaction {
            UserEntity.find {
                UsersTable.username eq username
            }.firstOrNull()
        }
    }
}