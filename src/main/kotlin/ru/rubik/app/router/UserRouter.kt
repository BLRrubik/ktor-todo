package ru.rubik.app.router

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.rubik.app.request.user.UserLoginRequest
import ru.rubik.app.request.user.UserRegisterRequest
import ru.rubik.app.service.user.UserService

fun Application.authRouter(userService: UserService) {
    routing {
        route("/auth") {
            post("/login") {
                call.respond(userService.loginUser(call.receive<UserLoginRequest>()))
            }
            post("/register") {
                call.respond(userService.registerUser(call.receive<UserRegisterRequest>()))
            }
        }
    }
}