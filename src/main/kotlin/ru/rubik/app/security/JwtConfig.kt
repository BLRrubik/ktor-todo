package ru.rubik.app.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm

class JwtConfig private constructor(secret: String) {
    private val algorithm = Algorithm.HMAC256(secret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .build()

    fun createToken(id: Long): String = JWT
        .create()
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .withClaim(CLAIM, id)
        .sign(algorithm)

    companion object {
        private const val ISSUER = "ktor-todo-issuer"
        private const val AUDIENCE = "ktor-todo-audience"
        const val CLAIM = "user-id"

        lateinit var instance: JwtConfig
            private set

        fun init(secret: String) {
            if (!this::instance.isInitialized) {
                instance = JwtConfig(secret)
            }
        }
    }
}