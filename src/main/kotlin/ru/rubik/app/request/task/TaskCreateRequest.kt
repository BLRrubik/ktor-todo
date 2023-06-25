package ru.rubik.app.request.task

import kotlinx.serialization.Serializable

@Serializable
data class TaskCreateRequest(
    val context: String
)
