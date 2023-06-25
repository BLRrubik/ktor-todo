package ru.rubik.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.*
import ru.rubik.app.router.authRouter
import ru.rubik.app.router.taskRouter
import ru.rubik.app.service.task.TaskService
import ru.rubik.app.service.user.UserService

fun Application.configureRouting (
    taskService: TaskService,
    userService: UserService
) {
    taskRouter(taskService)
    authRouter(userService)

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
}
