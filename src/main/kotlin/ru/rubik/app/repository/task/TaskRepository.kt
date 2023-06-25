package ru.rubik.app.repository.task

import ru.rubik.app.entity.TaskEntity
import ru.rubik.app.entity.UserEntity
import ru.rubik.app.request.task.TaskCreateRequest

interface TaskRepository {
    suspend fun getTasks(userId: Long): List<TaskEntity>
    suspend fun saveTask(request: TaskCreateRequest, userEntity: UserEntity): TaskEntity
}