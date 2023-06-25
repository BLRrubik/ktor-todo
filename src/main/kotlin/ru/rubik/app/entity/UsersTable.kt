package ru.rubik.app.entity

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction
import ru.rubik.app.dto.AuthUserDto
import ru.rubik.app.dto.UserDto

object UsersTable: LongIdTable("users") {
    val username = varchar("username", 255)
    val password = varchar("password", 255)
}

class UserEntity(id: EntityID<Long>) : Entity<Long>(id) {
    companion object : EntityClass<Long, UserEntity>(UsersTable)

    var username by UsersTable.username
    var password by UsersTable.password

    fun toDto(): UserDto {
        return UserDto(
            id.value, username
        )
    }

    fun toAuthDto(token: String): AuthUserDto {
        return AuthUserDto(
            id.value, username, token
        )
    }
}