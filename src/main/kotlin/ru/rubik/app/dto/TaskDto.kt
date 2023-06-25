package ru.rubik.app.dto

import ru.rubik.app.entity.TaskStatus

data class TaskDto(
    val id: Long,
    val context: String,
    val status: TaskStatus,
    val owner: UserDto
)
