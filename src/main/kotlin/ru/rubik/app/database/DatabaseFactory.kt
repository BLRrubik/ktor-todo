package ru.rubik.app.database

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import ru.rubik.app.entity.TasksTable
import ru.rubik.app.entity.UsersTable

object DatabaseFactory {

    private val config = HoconApplicationConfig(ConfigFactory.load())

    private val dbUser: String by lazy { config.property("db.user").getString() }
    private val dbPassword: String by lazy { config.property("db.password").getString() }
    private val dbDriver: String by lazy { config.property("db.driver").getString() }
    private val dbUrl: String by lazy { config.property("db.url").getString() }

    fun init() {
        Database.connect(hikari())

        transaction {
            SchemaUtils.create(TasksTable)
            SchemaUtils.create(UsersTable)
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.jdbcUrl = dbUrl
        config.username = dbUser
        config.password = dbPassword
        config.driverClassName = dbDriver
        config.maximumPoolSize = 3
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.isAutoCommit = false
        config.validate()

        return HikariDataSource(config)
    }
}