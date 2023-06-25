package ru.rubik.app.service.user.impl

import ru.rubik.app.repository.user.UserRepository
import ru.rubik.app.request.user.UserLoginRequest
import ru.rubik.app.request.user.UserRegisterRequest
import ru.rubik.app.response.BaseResponse
import ru.rubik.app.security.JwtConfig
import ru.rubik.app.security.encode
import ru.rubik.app.service.user.UserService

class UserServiceImpl (
    private val userRepository: UserRepository
): UserService {
    override suspend fun registerUser(request: UserRegisterRequest): BaseResponse<Any> {
        if (userRepository.findByUsername(request.username) != null) {
            return BaseResponse.ConflictResponse("User with ${request.username} is already exists")
        }

        userRepository.saveUser(request)

        return BaseResponse.SuccessResponse(mapOf("message" to "Success registration"))
    }

    override suspend fun loginUser(request: UserLoginRequest): BaseResponse<Any> {
        val user = userRepository.findByUsername(request.username)
            ?: return BaseResponse.NotFoundResponse("User with ${request.username} is not found")

        if (user.password != encode(request.password)) {
            return BaseResponse.AuthErrorResponse("Password is not correct")
        }

        val token = JwtConfig.instance.createToken(user.id.value)

        return BaseResponse.SuccessResponse(user.toAuthDto(token))
    }
}