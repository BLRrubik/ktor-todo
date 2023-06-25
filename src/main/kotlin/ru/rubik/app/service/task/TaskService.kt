package ru.rubik.app.service.task

import ru.rubik.app.request.task.TaskCreateRequest
import ru.rubik.app.response.BaseResponse
import ru.rubik.app.security.UserPrincipal

interface TaskService {
    suspend fun getTasks(principal: UserPrincipal): BaseResponse<Any>
    suspend fun createTask(request: TaskCreateRequest, principal: UserPrincipal): BaseResponse<Any>
}