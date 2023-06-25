package ru.rubik.app.entity

import org.jetbrains.exposed.sql.Table

object UsersTable: Table("users") {
    val id = long("user_id").primaryKey().autoIncrement()
    val username = varchar("username", 255)
    val password = varchar("password", 255)
}