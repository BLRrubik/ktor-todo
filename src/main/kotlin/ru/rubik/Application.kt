package ru.rubik

import io.ktor.server.application.*
import ru.rubik.app.database.DatabaseFactory
import ru.rubik.app.repository.task.TaskRepository
import ru.rubik.app.repository.task.impl.TaskRepositoryImpl
import ru.rubik.app.repository.user.UserRepository
import ru.rubik.app.repository.user.impl.UserRepositoryImpl
import ru.rubik.app.service.task.TaskService
import ru.rubik.app.service.task.impl.TaskServiceImpl
import ru.rubik.app.service.user.UserService
import ru.rubik.app.service.user.impl.UserServiceImpl
import ru.rubik.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    DatabaseFactory.init()

    val taskRepository: TaskRepository = TaskRepositoryImpl()
    val taskService: TaskService = TaskServiceImpl(taskRepository)

    val userRepository: UserRepository = UserRepositoryImpl()
    val userService: UserService = UserServiceImpl(userRepository)

    configureSecurity()
    configureHTTP()
    configureSerialization()
    configureRouting(taskService, userService)
}
