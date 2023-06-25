package ru.rubik.app.model

import kotlinx.serialization.Serializable
import ru.rubik.app.entity.TaskStatus

data class Task(
    val id: Long,
    val context: String,
    val status: TaskStatus
) {
    fun toDto(): TaskDto {
        return TaskDto(
            id, context, status
        )
    }
}

data class TaskDto(
    val id: Long,
    val context: String,
    val status: TaskStatus
)
