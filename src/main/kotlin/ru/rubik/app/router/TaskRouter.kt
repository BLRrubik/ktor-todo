package ru.rubik.app.router

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.rubik.app.request.task.TaskCreateRequest
import ru.rubik.app.security.UserPrincipal
import ru.rubik.app.service.task.TaskService

fun Application.taskRouter(taskService: TaskService) {
    routing {
        authenticate {
            route("/tasks") {
                get("") {
                    call.respond(taskService.getTasks())
                }
                post("") {
                    val request = call.receive<TaskCreateRequest>()

                    call.respond(HttpStatusCode.OK, taskService.createTask(request) ?: "Error")
                }
            }
        }
    }

}