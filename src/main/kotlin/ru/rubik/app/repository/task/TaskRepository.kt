package ru.rubik.app.repository.task

import ru.rubik.app.model.Task
import ru.rubik.app.request.task.TaskCreateRequest

interface TaskRepository {
    suspend fun getTasks(): List<Task?>
    suspend fun saveTask(request: TaskCreateRequest): Task?
}