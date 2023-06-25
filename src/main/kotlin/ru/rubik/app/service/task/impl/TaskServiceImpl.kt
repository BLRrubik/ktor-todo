package ru.rubik.app.service.task.impl

import ru.rubik.app.entity.UsersTable.entityId
import ru.rubik.app.repository.task.TaskRepository
import ru.rubik.app.repository.user.UserRepository
import ru.rubik.app.request.task.TaskCreateRequest
import ru.rubik.app.response.BaseResponse
import ru.rubik.app.security.UserPrincipal
import ru.rubik.app.service.task.TaskService

class TaskServiceImpl(
    private val taskRepository: TaskRepository,
    private val userRepository: UserRepository
) : TaskService {
    override suspend fun getTasks(principal: UserPrincipal): BaseResponse<Any> {
        val tasks = taskRepository.getTasks(principal.id)

        return BaseResponse.SuccessResponse(tasks.map { it.toDto() })
    }

    override suspend fun createTask(request: TaskCreateRequest, principal: UserPrincipal): BaseResponse<Any> {
        val user = userRepository.findById(principal.id)
            ?: return BaseResponse.NotFoundResponse(mapOf("message" to "User with id ${principal.id} is not found"))

        val task = taskRepository.saveTask(request, user)

        return if (task == null) BaseResponse.InternalResponse("message" to "Error during saving task")
        else BaseResponse.SuccessResponse(task.toDto())
    }

}