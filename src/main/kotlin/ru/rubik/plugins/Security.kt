package ru.rubik.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import ru.rubik.app.security.JwtConfig
import ru.rubik.app.security.UserPrincipal

fun Application.configureSecurity() {
    JwtConfig.init("secret")
    install(Authentication) {
         jwt {
             verifier(JwtConfig.instance.verifier)
             validate {
                 val claim = it.payload.getClaim(JwtConfig.CLAIM).asLong()
                 if (claim != null) {
                     UserPrincipal(claim)
                 }
                 else {
                     null
                 }
             }
         }
    }
}
