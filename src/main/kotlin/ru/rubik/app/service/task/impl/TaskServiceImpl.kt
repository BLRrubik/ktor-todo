package ru.rubik.app.service.task.impl

import ru.rubik.app.repository.task.TaskRepository
import ru.rubik.app.request.task.TaskCreateRequest
import ru.rubik.app.response.BaseResponse
import ru.rubik.app.service.task.TaskService

class TaskServiceImpl(
    private val taskRepository: TaskRepository
) : TaskService {
    override suspend fun getTasks(): BaseResponse<Any> {
        val tasks = taskRepository.getTasks()

        return BaseResponse.SuccessResponse(tasks.map { it?.toDto() })
    }

    override suspend fun createTask(request: TaskCreateRequest): BaseResponse<Any> {
        val task = taskRepository.saveTask(request)

        return if (task == null) BaseResponse.ConflictResponse("Error during creating task")
        else BaseResponse.SuccessResponse(task.toDto())
    }

}