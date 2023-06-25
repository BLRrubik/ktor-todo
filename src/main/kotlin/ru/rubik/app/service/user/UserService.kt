package ru.rubik.app.service.user

import ru.rubik.app.request.user.UserLoginRequest
import ru.rubik.app.request.user.UserRegisterRequest
import ru.rubik.app.response.BaseResponse

interface UserService {
    suspend fun registerUser(request: UserRegisterRequest): BaseResponse<Any>
    suspend fun loginUser(request: UserLoginRequest): BaseResponse<Any>
}