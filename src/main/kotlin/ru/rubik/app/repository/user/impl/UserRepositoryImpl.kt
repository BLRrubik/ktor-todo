package ru.rubik.app.repository.user.impl

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement
import ru.rubik.app.database.DatabaseFactory.dbQuery
import ru.rubik.app.entity.UsersTable
import ru.rubik.app.model.User
import ru.rubik.app.repository.user.UserRepository
import ru.rubik.app.request.user.UserRegisterRequest

class UserRepositoryImpl: UserRepository {
    override suspend fun saveUser(request: UserRegisterRequest): User? {
        var statement: InsertStatement<Number>? = null

        dbQuery {
            statement = UsersTable.insert {
                it[username] = request.username
                it[password] = request.password
            }
        }

        return rowToUser(statement?.resultedValues?.get(0))
    }

    override suspend fun findById(id: Long): User? {
        var row: ResultRow? = null
        dbQuery {
            row = UsersTable.select { UsersTable.id.eq(id) }.firstOrNull()
        }

        return rowToUser(row)
    }

    override suspend fun findByUsername(username: String): User? {
        var row: ResultRow? = null
        dbQuery {
            row = UsersTable.select { UsersTable.username.eq(username) }.firstOrNull()
        }
        return rowToUser(row)
    }

    private fun rowToUser(row: ResultRow?): User? {
        return if (row == null) null
        else User(
            row[UsersTable.id],
            row[UsersTable.username],
            row[UsersTable.password]
        )
    }
}