package ru.rubik

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.testing.*
import kotlin.test.*
import io.ktor.http.*
import ru.rubik.app.repository.task.TaskRepository
import ru.rubik.app.repository.task.impl.TaskRepositoryImpl
import ru.rubik.app.repository.user.UserRepository
import ru.rubik.app.repository.user.impl.UserRepositoryImpl
import ru.rubik.app.service.task.TaskService
import ru.rubik.app.service.task.impl.TaskServiceImpl
import ru.rubik.app.service.user.UserService
import ru.rubik.app.service.user.impl.UserServiceImpl
import ru.rubik.plugins.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        val taskRepository: TaskRepository = TaskRepositoryImpl()
        val taskService: TaskService = TaskServiceImpl(taskRepository)

        val userRepository: UserRepository = UserRepositoryImpl()
        val userService: UserService = UserServiceImpl(userRepository)

        application {
            configureRouting(taskService, userService)
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }
}
