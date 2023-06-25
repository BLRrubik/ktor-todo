package ru.rubik.app.service.task

import ru.rubik.app.model.Task
import ru.rubik.app.request.task.TaskCreateRequest
import ru.rubik.app.response.BaseResponse

interface TaskService {
    suspend fun getTasks(): BaseResponse<Any>
    suspend fun createTask(request: TaskCreateRequest): BaseResponse<Any>
}